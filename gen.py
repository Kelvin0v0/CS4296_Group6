from random import randint
import csv

friendList = []
numOfUser = 100

for i in range(numOfUser):
    friendList.append([])

for i in range(numOfUser):
    friendNum = randint(1,numOfUser-1)
    tmp = list(range(numOfUser))

    for j in range(friendNum):
        index = randint(0,len(tmp)-1)
        friend = tmp.pop(index)
        friendList[i].append(friend)
        friendList[friend].append(i)

for x in friendList:
    x = list(set(x))

followeeList = []

for i in range(numOfUser):
    tmp = list(range(numOfUser))
    tmpFollowee = []
    followeeNum = randint(0,numOfUser-1)
    for j in range(followeeNum):
        index = randint(0,len(tmp)-1)
        tmpFollowee.append(tmp.pop(index))
    followeeList.append(tmpFollowee)

likeRecord = []

for i in range(numOfUser):
    likeNum = randint(0,numOfUser)
    tmpLikeRecord = []
    for j in range(likeNum):
        likeBean = "({},{})".format(randint(0,100),randint(0,numOfUser))
        tmpLikeRecord.append(likeBean)
        tmpLikeRecord = list(set(tmpLikeRecord))
    likeRecord.append(tmpLikeRecord)

groupRecord = []

for i in range(numOfUser):
    groupRecord.append([randint(0,1),randint(0,1),randint(0,1),randint(0,1),randint(0,1)])

with open('facebookData.csv','w',newline='') as file:
    writer = csv.writer(file)
    writer.writerow(["UID","Friends","Followee","Like Records","Groups"])
    for i in range(numOfUser):
        writer.writerow([i,friendList[i],followeeList[i],likeRecord[i],groupRecord[i]])