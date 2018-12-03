package com.cts.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cts.vo.Task;

@Controller
public class TaskController {
	@Autowired
	TaskRepository trepo;
	
	List<Task> tasks = new ArrayList<Task>();
	@RequestMapping(value="/task")
	public ModelAndView getInsurance(ModelAndView model){
		model.setViewName("task");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/getTasks", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Task>> getTasks(ModelAndView model)
			throws Exception {
		tasks = new ArrayList<Task>();
		tasks = trepo.getTasks();
		/*Calendar c = Calendar.getInstance();
		c.set(2018, 07, 01);
		Date start1 = new java.util.Date(c.getTimeInMillis());
		c.set(2018, 11, 31);
		Date end1 = new java.util.Date(c.getTimeInMillis());
		c.set(2018, 07, 01);
		Date start2 = new java.util.Date(c.getTimeInMillis());
		c.set(2018, 11, 31);
		Date end2 = new java.util.Date(c.getTimeInMillis());
		
		tasks.add(new Task("parent Task",1,0,start1,end1,6,"",false));
		tasks.add(new Task("parent Task1",2,0,start2,end2,20,"",false));*/
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/addUpdateTask", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<List<Task>> addTask(@RequestBody Task task) 
			throws Exception{
		/*long length = tasks.size();
		String parentTask = "";
		if(task.getParentId() != 0){
			parentTask = ((Task)tasks.get((int)task.getParentId()-1)).getTask();
		}
		task.setParent(parentTask);
		if(task.getTaskId() == 0){
			task.setTaskId(length+1);
			tasks.add(task);
		}else{
			int taskIdForUpdation = (int)task.getTaskId() - 1;
			Task tempTask = tasks.get(taskIdForUpdation);
			tempTask.setEndDate(task.getEndDate());
			tempTask.setStartDate(task.getStartDate());
			tempTask.setTask(task.getTask());
			tempTask.setPriority(task.getPriority());
			tempTask.setParentId(task.getParentId());
		}*/
		if(task.getTaskId() == 0){
			trepo.addTask(task);
		}else{
			trepo.updateTask(task);
		}
		tasks = trepo.getTasks();
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK); 
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/endTask", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Task>> endTask(@RequestBody Task task) 
			throws Exception{
		/*int index = (int)task.getTaskId();
		tasks.get((int)index).setEndDate(new Date());
		tasks.get((int)index).setTaskended(true);*/	
		trepo.endTask(task);
		tasks = trepo.getTasks();
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK); 
	}
}
