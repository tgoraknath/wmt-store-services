# wmt-store-services
Walmart Product Catalog services - lookup and manage micro RESTful APIs.
## Table of Contents

- [Challenge](#Challenge)
- [Pre-requisite](#Pre-requisite)
- [Search API](#Search)
- [Manage API](#Manage)
- [Out Of Scope](#OutOfScope)
- [How Do I test](#Test)

# [Challenge](https://gist.github.com/daniyalzade/8e32cd266aebd6d2ce35)

[Standalone spring-boot java based RESTful](https://spring.io/guides/gs/rest-service/) micro and orchestration APIs to manage the walmart product catlog attributes and searchable products based on a search criteria : case-insensitive.

# Approach :)
- Let's go back to school and re-call how we use to memorize pages, sentences or even word(s).
  - while reading a book, we use to underline the word and make a note of page number.
  - Apply same principle for each item - determine the searchable fields either from short or long description, category path etc.
  - Utilize TrieNode data structure to maintain the collection of item or product ids.
  - Utilize same TrieNode to look up item id's based on input search string.

## Pre-requisite
* About 15 minutes
* Spring Tool Suite (STS)
* JDK 1.8 or later
* Maven 3.2+

## Search API /products?query={ any single word with alphabets }
- search API takes a string and returns array of Items with item id, short description, shipOptions, imgUrls, availability etc. 
  - Optional query fields are available to filter the records and apply pagination.
  

## Manage 
- Bulk uploads api: /manage/all uploads items with searchable fields from item long description into in-memory cache. For THA - a predefined set of item id's are choosed and will be part of search api response. 
- Single Item update or upload api: /manage/{item_id or product_id}: Uploads in-memory cache with searchable fields from given item long description and returns Item meta data.
- OOPs below search resulted in no data, no worries utilize below manage api's and try again.
  - http://localhost:8080/manage/35613901
  - http://localhost:8080/manage/35813552
  - http://localhost:8080/manage/23117408

## OutOfScope
- Error and Exception Handling | Alerts, Notification and Tools.
- Centralized or External cache.
- Bulk in-memory insertion.
- HTTP - POST, PUT and PATCH
- Partial Search or complete sentence or alphanumeric(with space).

## Test
* Import(Maven Project) wmt-item-services into STS: [git clone](https://github.com/tgoraknath/wmt-store-services.git)
* Right Click on Project
* Select Run As --> Spring Boot App
* console log: walmart.Application : Started Application in 1.266 seconds (JVM running for 1.639)
* Search Sample Requests
* http://localhost:8080/products?query=backpack
* http://localhost:8080/products?query=backPACK
* http://localhost:8080/products?query=backpACk
