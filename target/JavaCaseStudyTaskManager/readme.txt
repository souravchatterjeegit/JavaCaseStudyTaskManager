This is the task project with front end integrated with middle layer(Spring), 
with database connectivity.

It is assumed that mysql is there in the machine where the aplication is desired to be run.
The configuration will be -
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url" value="jdbc:mysql://localhost:3306/sakila" />
		<property name="username" value="root" />
		<property name="password" value="root" />

Please run the below queries in mysql before running the project-
create table task(
	task_id bigint not null auto_increment,
    parent_id bigint default 0,
    task_name varchar(50),
    start_date date,
    end_date date,
    priority int default 0 check(priority between 0 and 30),
    task_ended boolean default false,
    primary key(task_id)
);
create table parent_task(
	parent_id bigint not null auto_increment,
    parent_task varchar(50),
    primary key(parent_id)
);
insert into task values(1,0,'parent task','2018-08-01','2018-12-31',20,false);
insert into parent_task values(1,'parent task');
insert into task values(2,1,'another task','2018-11-01','2018-12-31',10,false);
insert into parent_task values(2,'another task');

BUILD :
The deployment script is given in POM.xml and can be found in 
\META-INF\maven\com.cts\JavaCaseStudyTaskManager\ directory of the war file.
		

deploy the JavaCaseStudyTaskManager.war in tomcat server and view the application -
http://localhost:8080/JavaCaseStudyTaskManager/task

The application will -
create a task,
show the tasks list (click 'View Task' link from top navigation),
update a task (clicking the update button corresponding to the task row, update and save )
end a task (the task cannot be updated, update and end button will be disabled in list)

End task will end the task in today's date. The end date will be today's date.