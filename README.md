# Document Services

 Document Service REST API that supports the document operations. 

## Description
 
Implement a Document Service REST API that supports the document operations. In particular, a document has a name, and one or more revisions. Each revision has a file uploaded by a user, some notes on the revision (optional), and a timestamp of when the revision was created or updated. The API should include the following endpoints (i.e. operations):

 * Get all documents. For each document, include the document name, number of revisions, and the timestamp of the latest revision. You may include additional information in the response if you want (and this applies to all operations), though the actual files should not be included.

 * Get a document. The response should include the document name and all its revisions in order. For each revision, include the notes, the timestamp, and a link to download the file for that revision -- note that it's a link to download the file, not the file itself.

 * Download the file of a revision. The response should include a Content-Disposition header with the value "attachment" and the original name of the uploaded file.

 * Add a document, which creates the first revision of the document. The request should include the name of the document, some notes (optional), and a file.

 * Add a new revision to a document.
 
 * Edit a revision -- only the revision notes can be edited, not the file.

> Note that we do NOT need to implement any UI or users' accesses -- just a generic document service that can be used by any application.
