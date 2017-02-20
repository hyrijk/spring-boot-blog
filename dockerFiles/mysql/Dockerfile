FROM mysql:5.6
ADD charset.cnf /etc/mysql/conf.d/charset.cnf

ADD database.sql /docker-entrypoint-initdb.d

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata