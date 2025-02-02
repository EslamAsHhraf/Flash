<div align= >

# ⚡ Flash

</div>
<p align="center">
   <img align="center" height="450px"  src="https://user-images.githubusercontent.com/71986226/185804922-31aa1626-c1a1-4a92-a5eb-cba79cf6ab5b.jpg" alt="logo">
</p>

<p align="center"> 
    <br> 
</p>

## 📝 Table of Contents

- <a href ="#about"> 📙 Overview</a>
- <a href ="#Started"> 💻 Get Started</a>
- <a href ="#Work"> 🧱 Search Engine Modules </a>
- <a href ="#Video"> 📽 Demo</a>
- <a href ="#Contributors"> ✨ Contributors</a>
- <a href ="#License"> 🔒 License</a>
<hr style="background-color: #4b4c60"></hr>

<a id = "about"></a>

## 📙 Overview

<ul>
<li> The aim of this project is to develop a simple Crawler- based search engine that demonstrates the main features of a search engine
and the interaction between them.</li>
<li> The main features of a search engine</li>
<ul>
<li> Web Crawling</li>
<li> Indexing</li> 
<li> Ranking</li>
</ul>
<br>
<li> Built using <a href="https://en.wikipedia.org/wiki/Java_(programming_language)">Java lnaguage</a>.</li>
<li>  Web interface  for  Search Engine  using <a href="https://en.wikipedia.org/wiki/HTML">Html</a> & <a href="https://en.wikipedia.org/wiki/CSS">CSS</a> & <a href="https://en.wikipedia.org/wiki/JavaScript">JS</a>.</li>

<li> Built using <a href="https://en.wikipedia.org/wiki/MongoDB">MongoDB</a>.</li>
</ul>
<hr style="background-color: #4b4c60"></hr>
<a id = "Started"></a>

## 🚀 Get Started

<ol>
<li>Clone the repository.

<br>

```
git clone https://github.com/AbdelrahmanHamdyy/Flash
```

</li>
<li> You will need to download <a href="https://www.oracle.com/java/technologies/downloads/">Jdk</a>. </li>
<li> You will need to download <a href="https://tomcat.apache.org/download-90.cgi">Tomcat</a>. </li>
<li> You will need to read <a href="https://github.com/abdelrahman0123/Flash/blob/main/Search%20Engine%20Project%20.pdf">Search Engine Project</a> to understand the project very well and how the search engine operates. </li>
</ol>
<hr style="background-color: #4b4c60"></hr>
<a id = "Work"></a>

## 🔎 Search Engine Modules

<table align="left;">
<tr>
<th width=23%>Module</th>
<th>Description</th>
</tr>
<tr>
<td> 🔷 Web Crawler</td>
<td>The web crawler is a software agent that collects documents from the web. The crawler starts with a list of URL addresses (seed set). It downloads the documents identified by these URLs and extracts hyper-links from them. The extracted URLs are added to the list of URLs to be downloaded. Thus, web crawling is a recursive process.</td>
</tr>
<tr>
<td>🔶 Indexer</td>
<td>The output of web crawling process is a set of downloaded HTML documents. To respond to user queries fast enough, the contents of these documents have to be indexed in a data structure that stores the words contained in each document and their importance (e.g., whether they are in the title, in a header or in plain text).</td>
</tr>
<tr>
<td> 🔷 Query Processor</td>
<td>This module receives search queries, performs necessary preprocessing and searches the index for relevant documents. Retrieve documents containing words that share the same stem with those in the search query. For example, the search query “travel” should match (with lower degree) the words “traveler”, “traveling” … etc.</td>
</tr>
<tr>
<td>🔶 Phrase Searching</td>
<td>Search engines will generally search for words as phrases when quotation marks are placed around the phrase.</td>
</tr>
<tr>
<td>🔷 Ranker</td>
<td>
<p>The ranker module sorts documents based on their popularity and relevance to the search query.
</p>
<ol>
<li>Relevance</li>
<p>Relevance is a relation between the query words and the result page and could be calculated in several ways such as tf-idf of the query word in the result page or simply whether the query word appeared in the title, heading, or body. And then you aggregate the scores from all query words to produce the final page relevance score.</p>
<li>Popularity</li>
<p>Popularity is a measure for the importance of any web page regardless the requested query. You can use pagerank algorithm (as explained in the lecture) or other ranking algorithms to calculate each page popularity.</p>
</ol>
</td>
</tr>
<tr>
<td>🔶 Voice Recognition Search</td>
<td>Using a voice query instead of a typed one.</td>
<tr>
<td>🔷 Web Interface</td>
<td><p>We  implement a web interface for  search engine.</p> 
<ul>
<li>This interface receives user queries and displays the resulting pages returned by the engine</li>
<br>
<li>The result appears with snippets of the text containing queries words. The output looks like google's results page.</li>
</ul>
</td>
</tr>
</table>

<hr style="background-color: #4b4c60"></hr>
<a id ="Video"></a>

## 🎥 Demo

<div  align="center">
  <img align="center" height=370px  src="https://user-images.githubusercontent.com/71986226/185816905-080167a3-b937-487f-a587-5dad5b310ce0.gif">
</div>
<hr style="background-color: #4b4c60"></hr>
<div  align="center">
<video src="https://user-images.githubusercontent.com/71986226/185817750-c0768762-230c-4562-a414-bfe79ef07c1d.mp4"   >
</video> 
</div>

<hr style="background-color: #4b4c60"></hr>

## 👑 Contributors <a id ="Contributors"></a>

<table align="center" >
  <tr>
    <td align="center"><a href="https://github.com/AbdelrahmanHamdyy"><img src="https://avatars.githubusercontent.com/u/67989900?v=4" width="150;" alt=""/><br /><sub><b>Abdelrahman Hamdy</b></sub></a><br /></td>
       <td align="center"><a href="https://github.com/AbdelrahmanNoaman"><img src="https://avatars.githubusercontent.com/u/76150639?v=4" width="150;" alt=""/><br /><sub><b>Abdelrahman Noaman</b></sub></a><br /></td>
     <td align="center"><a href="https://github.com/AdhamAliAbdelAal" ><img src="https://avatars.githubusercontent.com/u/83884426?v=4" width="150;" alt=""/><br /><sub><b>Adham Ali</b></sub></a><br />
    </td>
     <td align="center"><a href="https://github.com/EslamAsHhraf"><img src="https://avatars.githubusercontent.com/u/71986226?v=4" width="150;" alt=""/><br /><sub><b>Eslam Ashraf</b></sub></a><br /></td>
  </tr>
</table>

<a id ="License"></a>

## 🔒 License

> **Note**: This software is licensed under MIT License, See [License](https://github.com/AbdelrahmanHamdyy/Flash/blob/main/LICENSE) for more information ©AbdelrahmanHamdyy.
