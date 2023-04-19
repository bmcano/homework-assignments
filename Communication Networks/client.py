# Brandon Cano
# Python 3.9

import socket
import sys

address = sys.argv[1] # ec2-3-142-225-109.us-east-2.compute.amazonaws.com
port = sys.argv[2] # 12345
studentID = sys.argv[3] # student ID

try:
    # create a UDP socket
    usocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_address = (address, int(port))
    send = usocket.sendto(studentID.encode(), server_address)
    print("UDP data sent: " + studentID)

    # response
    rec = usocket.recv(1460)
    info = rec.decode()
    address, port, secret_number = info.split(" ")
    print("UDP data received: {}, {}, {}".format(address, port, secret_number))

    # create a TCP socket
    tsocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    port = int(port)
    server_address = (address, port)
    usocket.close()

    tsocket.connect(server_address)

    # send message 
    msg = "hello"
    send = tsocket.send(msg.encode())
    print("TCP data send: " + msg)

    # response
    rec = tsocket.recv(1460)
    print("TCP data received: " + rec.decode())

    # send and recieve messages until quit
    while msg != "quit":
        msg = input("Next message to send? ")
        if msg != "quit":
            tsocket.send(msg.encode())
            print("TCP data send: " + msg)

            response = tsocket.recv(1460)
            print("TCP data received " + response.decode())

finally:
    print("closing connection")
    tsocket.close()
