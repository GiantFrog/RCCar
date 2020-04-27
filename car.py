from gpiozero import Servo
import socket

#create a Servo for each pin with an attached esc; a servo and an esc both control the same way
left = Servo(12)
right = Servo(13)
#create a udp datagram socket to listen on 192.168.0.1:60065
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind(("192.168.0.1", 60065))

#loop through two smaller loops forever
while True:
    #in this first loop, wait for a signal. break and proceed if it's from our app; wait for the next if it's not.
    while True:
        question, address = sock.recvfrom(19)
        if question.decode("utf-8") == "Are you our RC car?":
            sock.sendto('y'.encode("utf-8"), address)
            appAddress = address
            break

    #now that we have an app connected, receive and execute instructions from it.
    while True:
        controlIn, address = sock.recvfrom(2)

        #only obey the command if they come from the app's IP; otherwise, discard.
        if address == appAddress:
            leftSpeed = int.from_bytes(controlIn[:1], byteorder='big', signed=True)
            rightSpeed = int.from_bytes(controlIn[1:], byteorder='big', signed=True)

            #divide each speed by 127 to assign the esc a value between -1 and 1
            left.value(leftSpeed / 127)
            right.value(rightSpeed / 127)