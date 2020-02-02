import socket
import sys
import matplotlib.pyplot as plt    
def main():
    HOST, PORT = "", 5001
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
        sock.bind((HOST, PORT))
        while True:
            sock.listen()
            conn, addr = sock.accept()
            with conn:
                print('Connected by ', addr)
                warriorPercent = conn.recv(1024)
                treePercent = conn.recv(1024)
                shallowPercent = conn.recv(1024)
                if warriorPercent or treePercent or shallowPercent != 0:
                    break
    log = open(sys.argv[1] + "-progression.log", "a")
    temp = []
    temp = warriorPercent.decode("utf-8").split(' ')
    n = temp[0]
    for x in temp:
        if x >= n:
            n = x

   
    log.write(n)
    log.write("\n")
    log.close()
    allNums = []
    x = []
    y = []

# read in calculated stats from backend file
    with open(sys.argv[1] + "-progression.log", "r") as f:
        data = f.readlines()
        for line in data:
            allNums +=  (line.strip().split(' '))
# split stats into arrays x and y representing each axis
    for i in range(len(allNums)):
        x.append(i)
        y.append((allNums[i]))
    # create line plot
    plt.plot(x,y,color='blue', linestyle = 'solid', linewidth = 2,
        marker = 'o', markerfacecolor = 'blue', markersize = 4)
    # set x and y limit
    plt.ylim(0, 100)
    plt.xlim(0,len(x))
    # label axes and title graph
    plt.xlabel('Session')
    plt.ylabel('Score')
    plt.title('Yoga Progression')
    # set grid
    plt.grid(True)
    # save graph into image file
    plt.savefig(sys.argv[1] + '-' + "progress" + '.png')

if __name__ == "__main__":
    main()

