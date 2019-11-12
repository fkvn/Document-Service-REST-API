# Document Services

 Document Service REST API that supports the document operations. 

### Description
 
Implement a Document Service REST API that supports the document operations. In particular, a document has a name, and one or more revisions. Each revision has a file uploaded by a user, some notes on the revision (optional), and a timestamp of when the revision was created or updated. The API should include the following endpoints (i.e. operations):

 * Get all documents. For each document, include the document name, number of revisions, and the timestamp of the latest revision. You may include additional information in the response if you want (and this applies to all operations), though the actual files should not be included.

 * Get a document. The response should include the document name and all its revisions in order. For each revision, include the notes, the timestamp, and a link to download the file for that revision -- note that it's a link to download the file, not the file itself.

 * Download the file of a revision. The response should include a Content-Disposition header with the value "attachment" and the original name of the uploaded file.

 * Add a document, which creates the first revision of the document. The request should include the name of the document, some notes (optional), and a file.

 * Add a new revision to a document.
 
 * Edit a revision -- only the revision notes can be edited, not the file.

> Note that we do NOT need to implement any UI or users' accesses -- just a generic document service that can be used by any application.

### Prerequisites

* Java: JDK 8 or above.
* IDE: Eclipse (recommended), IntelliJ IDEA or any IDE supports running Java application.
* Database: MySQL 8 
* Database Management Tools: MySQL Workbench or any equivalent alternatives.

### Installing

To import the project into Eclipse, first clone the project into a local Git repository, then in Eclipse, use File -> Import ... -> Maven -> Existing Maven Projects to import the project into Eclipse.

```
1. Import the project into Eclipse by following the instructions below
```

1. Clone the project to your local disk (or your workspace in Eclipse if needed).
1. Open Eclipse and import project via File - Import - Maven - Existing Maven Projects.

```
2. Set up Database connection
```
1. Go to "src/main/resources"
1. Edit name of "application.properties.sample" to "application.properties"
1. fill out the database information needed.

```
3. Run the project as Java application 
```

## Running the tests

Install Postman in order to test the API implementations in 2 ways:

> 1. Go to the URL "http://localhost:8080/<api_design_mapping>" via Postman.

> 2. Import [json

## Deployment

Add additional notes about how to deploy is updated soon.

## Built With

* [SpringBoot](http://start.spring.io) - The web framework used
* [Eclipse](https://www.eclipse.org/downloads/packages/release/indigo/sr2/eclipse-ide-java-ee-developers) - IDE supported
* [MySQL](https://dev.mysql.com/downloads/mysql/) - The database environment used
* [Postman](https://www.getpostman.com) - API development

## Contributing

When contributing to this repository, please first discuss the change you wish to make via issue, email, or any other method with the owners of this repository before making a change.

Update the README.md with details of changes to the interface, this includes new environment variables, exposed ports, useful file locations and container parameters.
 

## Authors

* **Kevin Ngo** - *Initial work* - [fkvn](https://github.com/fkvn)

See also the list of [contributors](https://github.com/fkvn/Hiring_process/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/fkvn/Hiring_process/blob/master/LICENSE) file for details

## Acknowledgments

* Inspiration: Chengyu Sun, PH.D. - Professor of Department of Computer Science - California State University, Los Angeles
