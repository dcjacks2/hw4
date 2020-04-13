package com.hw4.hw4.controller;


import com.hw4.hw4.Models.SavedQuoteRepo;
import com.hw4.hw4.Models.Savedquote;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    SavedQuoteRepo savedQuoteRepo;

    @RequestMapping("/")
    public ModelAndView doHome(){
        ModelAndView mv = new ModelAndView("index");
        String Message = "";
        String Author = "";
        String quote = "";
        String apikey = "1020147a7emsh56b33cfb0e6179ap17f117jsnde2d38604f2e";
        try{
            URL url = new URL("https://150000-quotes.p.rapidapi.com/keyword/Life");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-rapidapi-host",  "150000-quotes.p.rapidapi.com");
            connection.setRequestProperty("x-rapidapi-key", apikey);
            connection.connect();
            BufferedReader r  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                json.append(line);
            }
            JSONObject obj = new JSONObject(json.toString());
            Message = obj.getString("message");
            Author = obj.getString("author");

            quote = Author + " said: " + Message;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        mv.addObject("savedquotelist" , savedQuoteRepo.findAll());
        mv.addObject("quote", quote);


        return mv;
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("id") String id, @RequestParam("quotessubmitted") String Quotessubmitted){
        ModelAndView mv = new ModelAndView("redirect:/");
        Savedquote quoteToSave ;

        quoteToSave = new Savedquote();
        quoteToSave.setId(UUID.randomUUID().toString());

        quoteToSave.setQuotessubmitted(Quotessubmitted);
        savedQuoteRepo.save(quoteToSave);
        mv.addObject("savedquotelist", savedQuoteRepo.findAll());
        return mv;
    }

}

