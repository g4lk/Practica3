package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller 
public class ChartsController {
		
		/********************************  CHARTS ******************************/
		@RequestMapping(value = "/charts")
		public String login(Model model) {	
			return "charts.html";
			//return "PieChart.php";	 
		}
		
		
}		