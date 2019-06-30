FROM gradle:jdk8 as builder

RUN export $PATH

COPY --chown=gradle:gradle . /shop/

WORKDIR /shop

RUN gradle build

RUN gradle runShop