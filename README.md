# Key persons analysis using MapReduce on Facebook dataset

The project aim to using Hadoop MapReduce to searching the key person in Facebook dataset.

## Requirements
- OS: Mac OS or Linux, windows (Not stable for Hadoop)
- Hadoop framework: https://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-common/SingleCluster.html
- Java version 1.8: https://java.com/zh_TW/download/
- Intellij (optional)

## Running in local

You can import the program in your local directory and test it.
Using the dataset from input folder:
```
ID,Friends_Score,Followee_Score,Like_Score,Group_Score
0,73,96,86,573
1,106,77,28,376
2,66,44,66,563
3,143,11,9,764
4,73,30,44,951
5,113,3,35,752
6,134,43,50,374
7,83,27,32,951
8,132,84,31,376
9,79,52,6,752
10,75,67,59,575
11,65,85,44,575
12,78,22,75,565
13,59,8,93,563
14,104,71,60,575
15,130,39,51,764
16,78,29,76,376
...
```
running the skyline program with program parameters in edit configure:  ``input_file_1000.csv Skyline.csv 9 random 3``

## Running in AWS
The program can test it in AWS as step below:
1. export the program as a runnable jar
2. upload the jar folder to the prepared AWS S3 bucket
3. use prepared AWS EMR to run for the test with the argument below:
```
  s3://<your S3 bucket>/transformed_data.csv
  s3://<your S3 bucket>/Skyline.csv
  9
  random
  3
```
4. run and check the result in the S3 bucket
5. you can view the part-00000 output result

## Result
The output folder will generate. The result in folder part-00000 will showed as below:
```
ID,Friends_Score ,Followee_Score ,Like_Score ,Group_Score
97 , 133.0 , 94.0 , 2.0 , 378.0
93 , 105.0 , 98.0 , 24.0 , 187.0
84 , 77.0 , 61.0 , 95.0 , 575.0
80 , 151.0 , 83.0 , 69.0 , 573.0
69 , 84.0 , 59.0 , 99.0 , 376.0
57 , 153.0 , 58.0 , 86.0 , 764.0
55 , 125.0 , 67.0 , 76.0 , 575.0
47 , 144.0 , 34.0 , 99.0 , 565.0
46 , 107.0 , 84.0 , 88.0 , 577.0
43 , 90.0 , 84.0 , 94.0 , 388.0
36 , 148.0 , 17.0 , 92.0 , 752.0
23 , 142.0 , 94.0 , 74.0 , 563.0
0 , 73.0 , 96.0 , 86.0 , 573.0
```
## Authors
- Chun Ho TSUI (63545880)
- Ao Liu (55203200)
- Guanming HUANG (55199741)
- Kam Hon LAU (55208914)
- Pui Yuen Antony CHOI (55221278)

