package com.csw.java.controller;

import com.csw.java.bean.VideoListItem;
import com.csw.java.site.ppekk.PpekkAPI;
import com.csw.java.utils.GSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/video")
public class VideoSearchController {

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public String searchVideo(Model model, String word) {
        if (word == null) {
            word = "";
        }
        List<VideoListItem> itemList = new PpekkAPI().search(word);
        model.addAttribute("title", word);
        model.addAttribute("videoListItems", GSONUtils.toJSONString(itemList));
        return "search";
    }

}
