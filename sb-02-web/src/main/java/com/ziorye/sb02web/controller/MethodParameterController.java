package com.ziorye.sb02web.controller;

import com.ziorye.sb02web.bean.Person;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MethodParameterController {
    @ResponseBody
    @GetMapping("/person/{personId}/pet/{petName}")
    public Map<String, Object> getPetsByPerson(@PathVariable String personId, @PathVariable String petName, @PathVariable Map<String, String> varMap) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("personId", personId);
        resultMap.put("petName", petName);
        resultMap.put("varMap", varMap);
        return resultMap;
    }

    @ResponseBody
    @GetMapping("/requestHeader")
    public Map<String, Object> requestHeader(@RequestHeader("Host") String host, @RequestHeader Map<String, String> headers) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("host", host);
        resultMap.put("headers", headers);

        return resultMap;
    }

    @ResponseBody
    @GetMapping("/requestParam")
    public Map<String, Object> requestParam(@RequestParam String name, @RequestParam String pet, @RequestParam Map<String, String> map) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("name", name);
        resultMap.put("pet", pet);
        resultMap.put("map", map);

        return resultMap;
    }

    @ResponseBody
    @PostMapping("/requestBody")
    public String requestBody(@RequestBody String requestBody) {
        return requestBody;
    }

    @GetMapping("/go")
    public String forwardToSuccessPage(HttpServletRequest request) {
        request.setAttribute("msg", "from forwardToSuccessPage");
        request.setAttribute("code", 188);

        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public String successPage(@RequestAttribute String msg, @RequestAttribute int code, HttpServletRequest request) {
        boolean result = msg.equals(request.getAttribute("msg")) && code == Integer.parseInt(request.getAttribute("code").toString());
        return "正确的返回结果应该等于true，实际结果等于" + result;
    }

    @GetMapping("mapAndModel")
    /**
     * Map 和 Model 类型的方法参数最终会以 request.setAttribute(.,.) 的方式存在于 request 的 attribute 中。
     */
    public String mapAndModelMethodParameter(Map<String, String> map, Model model, HttpServletRequest request) {
        map.put("mapKey", "mapValue");
        model.addAttribute("modelAttributeName", "modelAttributeValue");
        request.setAttribute("requestAttributeName", "requestAttributeValue");

        return "forward:/checkAttribute";
    }

    @ResponseBody
    @GetMapping("/checkAttribute")
    public String checkAttribute(HttpServletRequest request) {
        boolean result = "mapValue".equals(request.getAttribute("mapKey"))
                && "modelAttributeValue".equals(request.getAttribute("modelAttributeName"))
                && "requestAttributeValue".equals(request.getAttribute("requestAttributeName"));
        return "Map 和 Model 类型的方法参数最终会以 request.setAttribute(.,.) 的方式存在于 request 的 attribute 中。<br>" +
                "正确的返回结果应该等于true，实际结果等于" + result;
    }

    @ResponseBody
    @PostMapping("savePerson")
    public Person savePerson(Person person) {
        return person;
    }
}
