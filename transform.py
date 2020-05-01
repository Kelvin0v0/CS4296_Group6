import csv

scoreList=[]
count=0
groupsocre=[[0,0],[0,0],[0,0],[0,0],[0,0]]
grouplist=[]

with open('facebookData.csv', mode='r') as csvfile:
    reader = csv.DictReader(csvfile,delimiter=',')
    for row in reader:            
        count+=1
        sum=0
        rowList=[]
        friends_score=len(row['Friends'].split(","))+1
        rowList.append(friends_score)
        sum+=friends_score

        followee_score=len(row['Followee'].split(","))+1
        rowList.append(followee_score)
        sum+=followee_score

        like_score=row['Like Records'].count('(')
        rowList.append(like_score)
        sum+=like_score
        
        group = [int(i) for i in row['Groups'].strip('][').split(', ')]
        grouplist.append(group)
        for i in range(len(group)):
            if(group[i]==1):
                groupsocre[i][0]+=sum
                groupsocre[i][1]+=1
        scoreList.append(rowList)
    # print(grouplist)

    for i in range(5):
        groupsocre[i][0]=groupsocre[i][0]/groupsocre[i][1]

    # print(count)
    for i in range(count):
        s=0
        for j in range(5):
            if(grouplist[i][j]==1):
                s+=groupsocre[j][0]
        scoreList[i].append(s)  
    csvfile.close()

    # print(scoreList)

with open('transformed_data.csv', 'w') as csvfile_read:
    fieldnames = ["ID","Friends_Score","Followee_Score","Like_Score","Group_Score"]
    writer = csv.writer(csvfile_read)
    writer.writerow(fieldnames)
    for i in range(len(scoreList)):
        print([i,scoreList[i][0],scoreList[i][1],scoreList[i][2],scoreList[i][3]])
        writer.writerow([i,scoreList[i][0],scoreList[i][1],scoreList[i][2],scoreList[i][3]])
    

