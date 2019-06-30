#init
FROM gradle:jdk8 as builder

#change to dir
WORKDIR /shop/

#clone from git
RUN git clone https://github.com/Johann13/DP35019419.git ./

#print dir (for debuging
RUN pwd

#build
RUN gradle build

#start
RUN gradle runShop
