package com.java.servletPractise;

import com.wavemaker.filesearch.SearchEntry;
import com.wavemaker.filesearch.WordSearch;

import javax.servlet.*;
import java.lang.Integer.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ServletExample extends HttpServlet
{
    String folderPath,searchKey;
    boolean sequential;
    int numberOfConsumerThreads;
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws  IOException {

        String message = "Hello Servlets";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //WordSearch linearSearch = new WordSearch();
        out.println("<h1>" + "Hello Servlets" + "</h1>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Result of form data";
        WordSearch parallelSearch = new WordSearch();
        folderPath = request.getParameter("folder_path");
        searchKey = request.getParameter("search_key");

        try {
            parallelSearch.searchWord(request.getParameter("folder_path"),request.getParameter("search_key"),Integer.parseInt(request.getParameter("thread_count")),Boolean.parseBoolean(request.getParameter("sequential")));
        } catch (InterruptedException e) {
            throw new RuntimeException("exception occured due to threads");
        }
        Map<String,List<SearchEntry>> result = parallelSearch.getResult();
        Map<String, List<SearchEntry>> parallelSearchOutput = parallelSearch.getResult();
        String output="";
        output+="<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n";
        output+="<ol>\n";
        for(Map.Entry<String,List<SearchEntry>> entry : parallelSearchOutput.entrySet())
        {
            output +=
                    entry.getKey();

            List<SearchEntry> parallelSearch_Result = parallelSearchOutput.get(entry.getKey());
            Iterator parallelSearchIterator = parallelSearch_Result.iterator();
            output+="<li>";
            while(parallelSearchIterator.hasNext())
            {
                SearchEntry sampleEntry = (SearchEntry) parallelSearchIterator.next();
                output +=" Row number :"+sampleEntry.getRowNumber()+" Column Number :"+sampleEntry.getColumnNumber()+" ;";
            }
            output+="</li>\n";
        }
        output+= "</ol>\n"+
                "</body> "+
                "</html>";
        out.println(output);

    }

}
