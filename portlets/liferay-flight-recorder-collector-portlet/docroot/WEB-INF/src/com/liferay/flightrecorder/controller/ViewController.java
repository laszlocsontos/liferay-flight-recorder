package com.liferay.flightrecorder.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller(value = "ViewController")
@RequestMapping("VIEW")
public class ViewController {

	@RenderMapping
	public String view(RenderRequest request, RenderResponse response, Model model){
		return "view";
	}
} 