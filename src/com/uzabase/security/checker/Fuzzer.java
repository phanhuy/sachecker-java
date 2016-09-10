package com.uzabase.security.checker;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.uzabase.security.checker.utils.IssueSecurity;
import com.uzabase.security.checker.utils.SecurityType;
import com.uzabase.security.checker.utils.Settings;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Trung on 4/7/2016 2:36 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 *
 * Implements for  Fuzzing Technology
 */
public class Fuzzer {
    private WebClient webClient;
    private static List<URL> allUrls = new ArrayList<>();
    private static List<URL> checkedUrls = new ArrayList<>();
    private int timeGap;
    private boolean fullScan;
    private Settings settings;

    public static List<IssueSecurity> issues = new ArrayList<>();

    public Fuzzer(WebClient webClient, Settings settings) {
        issues.clear();
        allUrls.clear();
        checkedUrls.clear();
        this.webClient = webClient;
        this.settings = settings;
        webClient.setJavaScriptEnabled(true);
    }

    public List<IssueSecurity> getIssues() {
        return issues;
    }

    public void crawl() {
        this.timeGap = settings.getTimeGap();
        this.fullScan = (settings.getCompleteness().equals("full"));

//        System.out.println("Attempting to Log into web app");
//		loginWebApp(settings.getLogin(), settings.getUser(),
//				settings.getPassword());

//        System.out.println("Discovering links from host page");
        List<URL> pages;
        try {
            pages = discoverLinks(settings.getHome());
            System.out.println("Start print pages");
            for (URL url: pages)
                System.out.println(url);
            System.out.println("End print pages");

//        System.out.println("Crawling page inputs on all pages");
//		inputPageCrawl(pages);

//		tryCommonPasswords(settings.getLogin(), settings.getCommonUsernames(),
//				settings.getCommonPasswords());

//		tryPageGuessing(settings.getHome(), settings.getPageGuesses());
        checkForSanitized(pages);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Commented out by default, this can take awhile to run since it tries
        // every single fuzz vectory on every single text input on every page.
        // submitVectorsOnInputs(pages);
    }

    /**
     * This code is for showing how you can get all the links on a given page,
     * and visit a given URL
     *
     * @param pageURL
     * @return
     * @throws IOException
     * @throws MalformedURLException
     */
    private List<URL> discoverLinks(String pageURL) throws IOException,
            MalformedURLException {
        // List<URL> urls = new ArrayList<URL>();

        URL baseUrl = new URL(pageURL);

        if (!allUrls.contains(baseUrl)) {
            allUrls.add(baseUrl);
            System.out.println(baseUrl);
        }
        System.out.println("Start Page URL");
        HtmlPage page = getPage(pageURL);
        System.out.println("End Page URL");

        System.out.println("   Discovering links on " + page.toString());

        List<HtmlAnchor> links = page.getAnchors();

        for (HtmlAnchor link : links) {
            if (!fullScan) {
                if (Math.random() < 0.5)
                    continue;
            }

            URL url = page.getFullyQualifiedUrl(link.getHrefAttribute());

            System.out.println("      Link discovered: " + link.asText()
                    + " @URL=" + url);

            if (url.getHost().equals(baseUrl.getHost())
                    && !allUrls.contains(url)
                    && !url.equals(baseUrl)
                    && !(baseUrl.toString() + "#").equals(url.toString()))
                allUrls.add(url);
        }

//        System.out.println("\n");
        checkedUrls.add(baseUrl);
//        try {
//            for (URL url : allUrls) {
//                if (!checkedUrls.contains(url)) {
//                    discoverLinks(url.toString());
//                }
//            }
//        } catch (Exception e) {
//
//        }

        try {
            Thread.sleep(timeGap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allUrls;
    }

    /**
     * This code is for demonstrating techniques for submitting an HTML form.
     * Fuzzer code would need to be more generalized
     *
     * @throws FailingHttpStatusCodeException
     * @throws MalformedURLException
     * @throws IOException
     */
    @SuppressWarnings("unused")
    private void doFormPost() throws FailingHttpStatusCodeException,
            MalformedURLException, IOException {
    	
        //HtmlPage page = getPage("http://localhost:8080/bodgeit/product.jsp?prodid=26");
    	HtmlPage page = getPage("http://24h.com.vn");
        List<HtmlForm> forms = page.getForms();
        for (HtmlForm form : forms) {
            if (!fullScan) {
                if (Math.random() < 0.5)
                    continue;
            }

            String response;

            HtmlInput input = form.getInputByName("quantity");
            input.setValueAttribute("-5");
            HtmlSubmitInput submit = (HtmlSubmitInput) form
                    .getFirstByXPath("//input[@id='submit']");

            response = submit.<HtmlPage> click().getWebResponse()
                    .getContentAsString();
//            System.out.println(response);

            try {
                Thread.sleep(timeGap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method will print out all of the input fields and text fields for
     * the a given list of web pages.
     *
     * @param urls
     * @throws FailingHttpStatusCodeException
     * @throws MalformedURLException
     * @throws IOException
     */
    private void inputPageCrawl(List<URL> urls)
            throws FailingHttpStatusCodeException, MalformedURLException,
            IOException {

        try {
            for (URL url : urls) {
                if (!fullScan) {
                    if (Math.random() < 0.5)
                        continue;
                }

                try {

                    HtmlPage page = getPage(url);

//                    System.out.println("   Discovering inputs on "
//                            + page.toString());

                    List<HtmlForm> forms = page.getForms();

                    for (HtmlForm form : forms) {
                        for (HtmlElement e : form
                                .getHtmlElementsByTagName("input"))
                            System.out.println("      Input discovered: " + e);

                        for (HtmlElement e : form
                                .getHtmlElementsByTagName("textarea"))
                            System.out.println("      Input discovered " + e);
                    }

                    System.out.println("   Discovering cookies on "
                            + page.toString());

                    for (Cookie cookie : webClient.getCookieManager()
                            .getCookies()) {
                        System.out.println("      Cookie discovered: "
                                + cookie.toString());

                    }
                }

                catch (FailingHttpStatusCodeException e) {

                }

//                System.out.println("\n");
            }
        } catch (FailingHttpStatusCodeException e) {

        }

        try {
            Thread.sleep(timeGap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        webClient.getCookieManager().clearCookies();
    }

    private void loginWebApp(String pageURL, String user, String pass)
            throws FailingHttpStatusCodeException, MalformedURLException,
            IOException {

        HtmlPage page = getPage(pageURL);
        List<HtmlForm> forms = page.getForms();

        for (HtmlForm form : forms) {
            try {
                HtmlInput username = form.getInputByName("username");
                HtmlInput password = form.getInputByName("password");

                username.setValueAttribute(user);
                password.setValueAttribute(pass);

                HtmlSubmitInput submit = (HtmlSubmitInput) form
                        .getFirstByXPath("//input[@value='Login']");
                submit.<HtmlPage> click().getWebResponse().getContentAsString();
            } catch (ElementNotFoundException e) {
                System.out.println(e);
            }

            try {
                Thread.sleep(timeGap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        System.out.println("\n");
    }

    private void tryPageGuessing(String baseURL, List<String> guesses)
            throws FailingHttpStatusCodeException, MalformedURLException,
            IOException {
        System.out.println("Trying to guess pages");
        for (String guess : guesses) {
            try {

                System.out
                        .println("Trying " + baseURL.toString() + "/" + guess);
                webClient.getPage(baseURL.toString() + "/" + guess);

                System.out.println("Page found!");

            } catch (FailingHttpStatusCodeException e) {
                System.out.println("Page does not exist");
            }
        }

        try {
            Thread.sleep(timeGap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tryCommonPasswords(String pageURL, List<String> usernames,
                                    List<String> passwords) throws FailingHttpStatusCodeException,
            IOException {
        System.out.println("Trying to log in to web app with common passwords");
        HtmlPage page = webClient.getPage(pageURL);
        List<HtmlForm> forms = page.getForms();

        for (HtmlForm form : forms) {
            try {
                HtmlInput username = form.getInputByName("username");
                HtmlInput password = form.getInputByName("password");

                for (String user : usernames) {
                    for (String pass : passwords) {
                        username.setValueAttribute(user);
                        password.setValueAttribute(pass);
                        HtmlSubmitInput submit = (HtmlSubmitInput) form
                                .getFirstByXPath("//input[@value='Login']");
                        HtmlPage newPage = submit.<HtmlPage> click();

                        if (newPage.getWebResponse().getContentAsString()
                                .contains("Logout")
                                || newPage.getWebResponse()
                                .getContentAsString()
                                .contains("Sign Out")) {
                            System.out
                                    .println("Login Successful with username: "
                                            + user + " and password: " + pass);
                            webClient.getCookieManager().clearCookies();

                        }

                        else {
//                            System.out
//                                    .println("Login Unsuccessful with username: "
//                                            + user + " and password: " + pass);
                        }

                    }
                }
            } catch (ElementNotFoundException e) {
                System.out.println(e);
            }

            try {
                Thread.sleep(timeGap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Helper method for url strings. Defers to {@link Fuzzer#getPage(URL)}
     *
     * @param url
     * @return
     * @throws FailingHttpStatusCodeException
     * @throws IOException
     */
    private HtmlPage getPage(String url) throws FailingHttpStatusCodeException,
            IOException {
        try {
            return getPage(new URL(url));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    /**
     * Wrapper around {@link WebClient#getPage(URL)} so that every
     * request/response is checked for sensitive data.
     *
     * @param url
     *            The {@link URL} to get the page for
     * @return The HtmlPage
     * @throws FailingHttpStatusCodeException
     * @throws IOException
     */
    private HtmlPage getPage(URL url) throws FailingHttpStatusCodeException,
            IOException {
        //        String contents = page.getWebResponse().getContentAsString();

//        for (String sensitive : settings.getSensitiveData()) {
//            if (contents.contains(sensitive)) {
//                System.out.println("Warning: Sensitive data <" + sensitive
//                        + "> found in request for URL " + url);
//                System.out.println("Page:");
////                System.out.println(contents);
//            }
//        }

        return webClient.getPage(url);
    }

    @SuppressWarnings({ "unchecked", "unused" })
    private void submitVectorsOnInputs(List<URL> urls)
            throws FailingHttpStatusCodeException, IOException {
        HtmlPage page;
        List<HtmlInput> inputs;
        String response;
        HtmlSubmitInput submit;

        for (URL url : urls) {
            System.out.println("Getting page: " + url);
            page = getPage(url);
            System.out.println("End Getting page: " + url);
            inputs = (List<HtmlInput>) page
                    .getByXPath("/html/body//form//input[@type='text']");
            submit = page
                    .getFirstByXPath("/html/body//form//input[@type='submit']");

            for (HtmlInput input : inputs) {
                for (String vector : settings.getAllFuzzVectors()) {
                    input.setValueAttribute(vector);

                    response = submit.<HtmlPage> click().getWebResponse()
                            .getContentAsString();

                    // Look to see if we caused some type of error.
                    if (response.toLowerCase().contains("error")) {
                        System.out.println("Error caused by using vector "
                                + vector);
                        System.out.println("Input was: " + input.asText());
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void checkForSanitized(List<URL> urls) {
        HtmlPage page;
        List<HtmlInput> inputs;
        List<HtmlInput> inputsTotal = new ArrayList<>();
        String response;
        HtmlSubmitInput submit;
        for (URL url : urls) {
//            if (!fullScan) {
//                if (Math.random() < 0.5) continue;
//            }
//            URL url = urls.get(0);
            System.out.println("Checking for URL:" + url);
            try {
                System.out.println("Getting page: " + url);
                page = getPage(url);
                System.out.println("End getting page: " + url);

            inputs = (List<HtmlInput>) page
                    .getByXPath("/html/body//form//input[@type='text']");

            submit = page
                    .getFirstByXPath("/html/body//form//input[@type='submit']");
            List<HtmlInput> tempHtmlInputs = new ArrayList<>();
                System.out.println("Size of Inputs:" + inputs.size());

                for (HtmlInput newInput : inputs) {
                    int i = 0;
                    for (HtmlInput oldInput : inputsTotal)
                        if (Objects.equals(oldInput.getId(), newInput.getId()) && oldInput.getId() != null && newInput.getId() != null)
                            i = 1;
                    if (i == 0) {
                        inputsTotal.add(newInput);
                        tempHtmlInputs.add(newInput);
                    }
                }
                System.out.println("Size of temp inputs" + tempHtmlInputs.size());

            for (HtmlInput input : tempHtmlInputs) {
                System.out.println("Checking for Input:" + input);
                String inputTest = input.toString();
                // Testing for XSS
                for (String vector : settings.getXssFuzzVectors()) {
                    input.setValueAttribute(vector);
                    System.out.println("Checking with data:" + vector);
                    response = submit.<HtmlPage> click().getWebResponse()
                            .getContentAsString();
//                    System.out.println("Result from browser: "+ response);

                    int i = 0, j = 0;
                    if (response.contains(vector)) {
                        j = 1;
                        System.out.println("warning: " + vector + " in " + url + ", type XSS, With vector: " + vector);
                    }

                    for (String sensitive : settings.getSensitiveData()) {

                        if (response.contains(sensitive)) {
                            issues.add(new IssueSecurity(SecurityType.XSS, IssueSecurity.Severity.ERROR, vector, inputTest));
                            System.out.println("error: " + sensitive + " in " + url + ", type XSS, With vector: " + vector);
                            i = 1;
                            break;
                        }
                    }
                    if (i == 0 && j == 1)
                    issues.add(new IssueSecurity(SecurityType.XSS, IssueSecurity.Severity.WARNING, vector, inputTest));
                    else
                    if (i == 0)
                        issues.add(new IssueSecurity(SecurityType.XSS, IssueSecurity.Severity.NORMAL, vector, inputTest));
                }

                // Testing for XML
                for (String vector : settings.getXmlInjectionFuzzVectors()) {
                    input.setValueAttribute(vector);
                    System.out.println("Checking with data:" + vector);
                    response = submit.<HtmlPage>click().getWebResponse()
                            .getContentAsString();

                    int i = 0, j = 0;
                    if (response.contains(vector)) {
                        j = 1;
                        System.out.println("warning: " + vector + " in " + url + ", type XML, With vector: " + vector);
                    }

                    for (String sensitive : settings.getSensitiveData()) {

                        if (response.contains(sensitive)) {
                            issues.add(new IssueSecurity(SecurityType.XML_INJECTION, IssueSecurity.Severity.ERROR, vector, inputTest));
                            System.out.println("error: " + sensitive + " in " + url + ", type XML, With vector: " + vector);
                            i = 1;
                            break;
                        }
                    }
                    if (i == 0 && j == 1)
                        issues.add(new IssueSecurity(SecurityType.XML_INJECTION, IssueSecurity.Severity.WARNING, vector, inputTest));
                    else
                    if (i == 0)
                        issues.add(new IssueSecurity(SecurityType.XML_INJECTION, IssueSecurity.Severity.NORMAL, vector, inputTest));
                }

                // Testing for Xpath
                for (String vector : settings.getXpathInjectionFuzzVectors()) {
                    input.setValueAttribute(vector);
                    System.out.println("Checking with data:" + vector);
                    response = submit.<HtmlPage>click().getWebResponse()
                            .getContentAsString();
                    int i = 0, j = 0;
                    if (response.contains(vector)) {
                        j = 1;
                        System.out.println("warning: " + vector + " in " + url + ", type XPath, With vector: " + vector);
                    }

                    for (String sensitive : settings.getSensitiveData()) {

                        if (response.contains(sensitive)) {
                            issues.add(new IssueSecurity(SecurityType.XPATH_INJECTION, IssueSecurity.Severity.ERROR, vector, inputTest));
                            System.out.println("error: " + sensitive + " in " + url + ", type XPath, With vector: " + vector);
                            i = 1;
                            break;
                        }
                    }
                    if (i == 0 && j == 1)
                        issues.add(new IssueSecurity(SecurityType.XPATH_INJECTION, IssueSecurity.Severity.WARNING, vector, inputTest));
                    else
                    if (i == 0)
                        issues.add(new IssueSecurity(SecurityType.XPATH_INJECTION, IssueSecurity.Severity.NORMAL, vector, inputTest));
                }

                // Testing for SQL
                for (String vector : settings.getSqlInjectionFuzzVectors()) {
                    input.setValueAttribute(vector);
                    System.out.println("Checking with data:" + vector);
                    response = submit.<HtmlPage>click().getWebResponse()
                            .getContentAsString();
                    int i = 0, j = 0;
                    if (response.contains(vector)) {
                        j = 1;
                        System.out.println("warning: " + vector + " in " + url + ", type SQL, With vector: " + vector);
                    }

                    for (String sensitive : settings.getSensitiveData()) {

                        if (response.contains(sensitive)) {
                            issues.add(new IssueSecurity(SecurityType.SQL_INJECTION, IssueSecurity.Severity.ERROR, vector, inputTest));
                            System.out.println("error: " + sensitive + " in " + url + ", type SQL, With vector: " + vector);
                            i = 1;
                            break;
                        }
                    }
                    if (i == 0 && j == 1)
                        issues.add(new IssueSecurity(SecurityType.SQL_INJECTION, IssueSecurity.Severity.WARNING, vector, inputTest));
                    else
                    if (i == 0)
                        issues.add(new IssueSecurity(SecurityType.SQL_INJECTION, IssueSecurity.Severity.NORMAL, vector, inputTest));
                }
            }

            }
            catch (SocketTimeoutException e){
                System.out.println("Fucking error SocketTimeoutException");
            }
            catch (NullPointerException e) {
                System.out.println("Fucking error NullPointerException!");
            }
             catch (RuntimeException e) {
                System.out.println("Fucking error RuntimeException");
            } catch (IOException e) {
                System.out.println("Fucking error IOException");
            }
        }
        System.out.println("All input checked: ");
        System.out.println("**********************************");
        for (HtmlInput input : inputsTotal) {
            System.out.println(input);
        }
        System.out.println("**********************************");
    }

}
