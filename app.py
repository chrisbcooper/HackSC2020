import logging
import time
import edgeiq
import socket
import sys
import cv2
import math

"""
Use pose estimation to determine human poses in realtime. Human Pose returns
a list of key points indicating joints that can be used for applications such
as activity recognition and augmented reality.

Pose estimation is only supported using the edgeIQ container with an NCS
accelerator.
"""


def main(): #0,1,2,3
    pose_estimator = edgeiq.PoseEstimation("alwaysai/human-pose")

    pose_estimator.load(
            engine=edgeiq.Engine.DNN_OPENVINO,
            accelerator=edgeiq.Accelerator.MYRIAD)

    # im = cv2.imread("yoga3.tiff")
    # temp = pose_estimator.estimate(im)
    # temp.draw_poses(im)
    # pose = []
    # pose = temp.poses
    # print(pose[0]) #for creating standards of comparison

    print("Loaded model:\n{}\n".format(pose_estimator.model_id))
    print("Engine: {}".format(pose_estimator.engine))
    print("Accelerator: {}\n".format(pose_estimator.accelerator))

    fps = edgeiq.FPS()
    totalData = []
    finalPercent = 0

    try:
        with edgeiq.WebcamVideoStream(cam=0) as video_stream, \
                edgeiq.Streamer() as streamer:
            # Allow Webcam to warm up
            time.sleep(2.0)
            fps.start()

            # loop detection
            while True:
                #sock.connect((HOST, PORT))
                data = []
                frame = video_stream.read()
                results = pose_estimator.estimate(frame)
                # Generate text to display on streamer
                text = ["Model: {}".format(pose_estimator.model_id)]
                text.append(
                        "Inference time: {:1.3f} s".format(results.duration))
                for ind, pose in enumerate(results.poses):
                    text.append("Person {}".format(ind))
                    text.append('-'*10)
                    text.append("Key Points:")
                    for key_point in pose.key_points:
                        text.append(str(key_point))
                        pair = []
                        for num in key_point:  
                            pair.append(num)
                        data.append(pair)
                    totalData.append(data)
                streamer.send_data(results.draw_poses(frame))
                
                    
                fps.update()

                if streamer.check_exit():
                    break
    finally:
        fps.stop()
        print("elapsed time: {:.2f}".format(fps.get_elapsed_seconds()))
        print("approx. FPS: {:.2f}".format(fps.compute_fps()))

        print("Program Ending")
        

        # baseline = []
        # if sys.argv[1] == 0:
        #     base = warrior
        # if sys.argv[1] == 1:
        #     base = tree
        # if sys.argv[1] == 2:
        #     base = shallow
        base = []
        pose = [0, 0, 0, 0, 0, 0]
        warriorPercent = 0
        treePercent = 0
        shallowPercent = 0
        shallow = [79.88990344093179, 69.50973653793667, 
        66.54046940822788, 86.1176560637854, 64.91539574803389,
         71.15993236941121]

        warrior = [0.6648744779593332, 71.8926574880734, 83.39782440149496,
        2.6022986468209623, 39.767520796720184, 4.8571386415077376]

        tree = [85.62347333954862, 11.213883058379075, 1.3511860399991635, 
        88.63344955023356, 44.494915386235355, 29.167993097152067]


        for z in range(0, 3):
            percent = 0
            percentCount = 0
            
            if z == 0:
                base = warrior
            elif z == 1:
                base = tree
            else:
                base = shallow
            
            for twoD in totalData:

                pose = [0, 0, 0, 0, 0, 0]
                #Create the angles and add them to the back of the pose array
    
                nose = [twoD[0][0], twoD[0][1]]
    
                neck = [twoD[1][0], twoD[1][1]]
    
                rights = [twoD[2][0], twoD[2][1]]
    
                righte = [twoD[3][0], twoD[3][1]]
    
                rightw = [twoD[4][0], twoD[4][1]]
    
                lefts = [twoD[5][0], twoD[5][1]]
    
                lefte = [twoD[6][0], twoD[6][1]]
    
                leftw = [twoD[7][0], twoD[7][1]]
    
                righth = [twoD[8][0], twoD[8][1]]
    
                rightk = [twoD[9][0], twoD[9][1]]
    
                righta = [twoD[10][0], twoD[10][1]]
    
                lefth = [twoD[11][0], twoD[11][1]]
    
                leftk = [twoD[12][0], twoD[12][1]]
    
                lefta = [twoD[13][0], twoD[13][1]]
    
    
                #right arm angle
                if not(rights[1] == -1 or neck[1] == -1 or rightw[1] == -1):
                    if (rights[0] != neck[0] and rightw[0] != rights[0]):
                        rights_neck = -1 * ((rights[1] - neck[1])/(rights[0] - neck[0]))
                        rights_rightw = -1 * ((rightw[1] - rights[1])/(rightw[0] - rights[0]))
                        pose[0] = (math.degrees(math.atan((rights_neck - rights_rightw) / (1 + (rights_rightw) * (rights_neck)))))
        
    
    
                #right knee angle
                if not(righth[0] == -1 or neck[0] or righth[0] == -1 or rightk[0] == -1):
                    if (righth[0] != neck[0] and righth[0] != rightk[0]):
                        neck_righth = -1 * ((righth[1] - neck[1]) / (righth[0] - neck[0]))
                        righth_rightk = -1 * ((righth[1] - rightk[1]) / (righth[0] - rightk[1]))
                        pose[1] = (math.degrees(math.atan((neck_righth - righth_rightk)/ (1 + (neck_righth) * (righth_rightk)))))
    
    
    
                #right lower leg angle
                if not(rightk[0] == -1 or righta[0] or righth[0] == -1):
                    if (righta[0] != rightk[0]):
                        rightk_righta = -1 * ((righta[1] - rightk[1]) / (righta[0] - rightk[0]))
                        pose[2] = (math.degrees(math.atan((righth_rightk - rightk_righta)/ (1 + (rightk_righta) * (righth_rightk)))))\
    
    
    
                #left arm angle
                if not(lefts[0] == -1 or neck[0] or leftw[0] == -1):
                    if (lefts[0] != neck[0] and leftw[0] != lefts[0]):
                        lefts_neck = -1 * ((lefts[1] - neck[1])/(lefts[0] - neck[0]))
                        lefts_leftw = -1 * ((leftw[1] - lefts[1])/(leftw[0] - lefts[0]))
                        pose[3] = (math.degrees(math.atan((lefts_neck - lefts_leftw) / (1 + (lefts_leftw) * (lefts_neck)))))
    
    
    
                #left knee angle
                if not(lefts[0] == -1 or neck[0] == -1 or leftw[0] == -1 or lefth[0] == -1):
                    if (lefth[0] != neck[0] and lefth[0] != leftk[0]):
                        neck_lefth = -1 * ((lefth[1] - neck[1]) / (lefth[0] - neck[0]))      
                        lefth_leftk = -1 * ((lefth[1] - leftk[1]) / (lefth[0] - leftk[1]))
                        pose[4] = (math.degrees(math.atan((neck_lefth - lefth_leftk)/ (1 + (neck_lefth) * (lefth_leftk)))))
        
        
    
                #left lower leg angle
                if not(leftk[0] == -1 or lefta[0] or lefth[0] == -1):
                    if (lefta[0] != leftk[0]):
                        leftk_lefta = -1 * ((lefta[1] - leftk[1]) / (lefta[0] - leftk[0]))
                        pose[5] = (math.degrees(math.atan((lefth_leftk - leftk_lefta)/ (1 + (leftk_lefta) * (lefth_leftk)))))
    
    
    
                for c in range(0, len(pose)):
                    if pose[c] < 0:
                        pose[c] *= -1
    
                counter = 0
                percent = 0
                for b in range(0, len(pose)):
                    if pose[b] != 0:
                        counter += 1
                        percent += (180 - abs(base[b] - pose[b])) / 180 * 100
                if counter != 0:
                    percent /= counter

                if z == 0:
                    warriorPercent += percent
                elif z == 1:
                    treePercent += percent
                else:
                    shallowPercent += percent
                    
                if percent != 0:
                    percentCount += 1

            if percentCount != 0:
                if z == 0:
                    warriorPercent /= percentCount
                elif z == 1:
                    treePercent /= percentCount
                else:
                    shallowPercent /= percentCount



        HOST, PORT = "10.55.0.4", 5001 
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
            sock.settimeout(None)
            sock.connect((HOST, PORT))
            print(warriorPercent)
            print(treePercent)
            print(shallowPercent)
            warriorPercent = (str(int(warriorPercent)) + " ").encode("utf-8")
            treePercent = (str(int(treePercent)) + " ").encode("utf-8")
            shallowPercent = (str(int(shallowPercent)) + " ").encode("utf-8")
            print(warriorPercent)
            print(treePercent)
            print(shallowPercent)
            sock.send(warriorPercent)
            sock.send(treePercent)
            sock.send(shallowPercent)


if __name__ == "__main__":
    data = " ".join(sys.argv[1:])
    main()