# ssc-y21t2-backend-team-f
ssc-y21t2-backend-team-f created by GitHub Classroom
# Start up configuration
Application configuration: src/main/resources/application.properties

To start the application, you just have to have to configure this three lines <br>
spring.datasource.url=jdbc:mariadb://localhost:\<port\>/\<database name\> <br>
spring.datasource.username=\<Your username\> <br>
spring.datasource.password=\<Your password\> <br>
  
By default this will connect to mariadb on localhost:13306 on 'login_webapp' database
with the following configuration

spring.datasource.url=jdbc:mariadb://localhost:13306/login_webapp

And you only need to have a blank database named 'login_webapp' or whatever you choose for
the \<database name\> in order to run the application, the code will create and populate the table
for you.
  
As for the data that will be initially populated, you can find that in the class

src/main/java/io/muzoo/ssc/project/backend/init/InitApplicationRunner.java

Notice that the link for sample videos are 

http://157.245.155.41:8082/hls/sample1.mp4/index.m3u8

which can be access immediately without installing nginx-vod (It the video hosted on my server).
If you got you own nginx-vod, just change this link, but feel free to continue using the link there
or even request me to add more video to the server (since I already configured it so this is for the convenience
of testing).

If you have downloaded and runs lower version of this code the sample video link might still be

http://localhost:8082/hls/sample1.mp4/index.m3u8

which required to set up nginx-vod on the same port and have same video name as mine. If you wish to use the video link from my server, you can simply run sql query on mariadb to change the link to
the video link from my server. An even easier way to update is to just drop the tbl_video table and the rerun the code, the new table will be created and populated for you.
