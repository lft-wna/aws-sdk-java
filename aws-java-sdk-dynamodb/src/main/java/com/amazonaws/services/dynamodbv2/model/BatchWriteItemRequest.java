/*
 * Copyright 2010-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 * 
 *  http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

import com.amazonaws.AmazonWebServiceRequest;

/**
 * Container for the parameters to the {@link com.amazonaws.services.dynamodbv2.AmazonDynamoDB#batchWriteItem(BatchWriteItemRequest) BatchWriteItem operation}.
 * <p>
 * The <i>BatchWriteItem</i> operation puts or deletes multiple items in
 * one or more tables. A single call to <i>BatchWriteItem</i> can write
 * up to 16 MB of data, which can comprise as many as 25 put or delete
 * requests. Individual items to be written can be as large as 400 KB.
 * </p>
 * <p>
 * <b>NOTE:</b> BatchWriteItem cannot update items. To update items, use
 * the UpdateItem API.
 * </p>
 * <p>
 * The individual <i>PutItem</i> and <i>DeleteItem</i> operations
 * specified in <i>BatchWriteItem</i> are atomic; however
 * <i>BatchWriteItem</i> as a whole is not. If any requested operations
 * fail because the table's provisioned throughput is exceeded or an
 * internal processing failure occurs, the failed operations are returned
 * in the <i>UnprocessedItems</i> response parameter. You can investigate
 * and optionally resend the requests. Typically, you would call
 * <i>BatchWriteItem</i> in a loop. Each iteration would check for
 * unprocessed items and submit a new <i>BatchWriteItem</i> request with
 * those unprocessed items until all items have been processed.
 * </p>
 * <p>
 * Note that if <i>none</i> of the items can be processed due to
 * insufficient provisioned throughput on all of the tables in the
 * request, then <i>BatchWriteItem</i> will return a
 * <i>ProvisionedThroughputExceededException</i> .
 * </p>
 * <p>
 * <b>IMPORTANT:</b> If DynamoDB returns any unprocessed items, you
 * should retry the batch operation on those items. However, we strongly
 * recommend that you use an exponential backoff algorithm. If you retry
 * the batch operation immediately, the underlying read or write requests
 * can still fail due to throttling on the individual tables. If you
 * delay the batch operation using exponential backoff, the individual
 * requests in the batch are much more likely to succeed. For more
 * information, go to Batch Operations and Error Handling in the Amazon
 * DynamoDB Developer Guide.
 * </p>
 * <p>
 * With <i>BatchWriteItem</i> , you can efficiently write or delete large
 * amounts of data, such as from Amazon Elastic MapReduce (EMR), or copy
 * data from another database into DynamoDB. In order to improve
 * performance with these large-scale operations, <i>BatchWriteItem</i>
 * does not behave in the same way as individual <i>PutItem</i> and
 * <i>DeleteItem</i> calls would For example, you cannot specify
 * conditions on individual put and delete requests, and
 * <i>BatchWriteItem</i> does not return deleted items in the response.
 * </p>
 * <p>
 * If you use a programming language that supports concurrency, such as
 * Java, you can use threads to write items in parallel. Your application
 * must include the necessary logic to manage the threads. With languages
 * that don't support threading, such as PHP, you must update or delete
 * the specified items one at a time. In both situations,
 * <i>BatchWriteItem</i> provides an alternative where the API performs
 * the specified put and delete operations in parallel, giving you the
 * power of the thread pool approach without having to introduce
 * complexity into your application.
 * </p>
 * <p>
 * Parallel processing reduces latency, but each specified put and delete
 * request consumes the same number of write capacity units whether it is
 * processed in parallel or not. Delete operations on nonexistent items
 * consume one write capacity unit.
 * </p>
 * <p>
 * If one or more of the following is true, DynamoDB rejects the entire
 * batch write operation:
 * </p>
 * 
 * <ul>
 * <li> <p>
 * One or more tables specified in the <i>BatchWriteItem</i> request does
 * not exist.
 * </p>
 * </li>
 * <li> <p>
 * Primary key attributes specified on an item in the request do not
 * match those in the corresponding table's primary key schema.
 * </p>
 * </li>
 * <li> <p>
 * You try to perform multiple operations on the same item in the same
 * <i>BatchWriteItem</i> request. For example, you cannot put and delete
 * the same item in the same <i>BatchWriteItem</i> request.
 * </p>
 * </li>
 * <li> <p>
 * There are more than 25 requests in the batch.
 * </p>
 * </li>
 * <li> <p>
 * Any individual item in a batch exceeds 400 KB.
 * </p>
 * </li>
 * <li> <p>
 * The total request size exceeds 16 MB.
 * </p>
 * </li>
 * 
 * </ul>
 *
 * @see com.amazonaws.services.dynamodbv2.AmazonDynamoDB#batchWriteItem(BatchWriteItemRequest)
 */
public class BatchWriteItemRequest extends AmazonWebServiceRequest implements Serializable {

    /**
     * A map of one or more table names and, for each table, a list of
     * operations to be performed (<i>DeleteRequest</i> or
     * <i>PutRequest</i>). Each element in the map consists of the following:
     * <ul> <li> <p><i>DeleteRequest</i> - Perform a <i>DeleteItem</i>
     * operation on the specified item. The item to be deleted is identified
     * by a <i>Key</i> subelement: <ul> <li> <p><i>Key</i> - A map of primary
     * key attribute values that uniquely identify the ! item. Each entry in
     * this map consists of an attribute name and an attribute value. For
     * each primary key, you must provide <i>all</i> of the key attributes.
     * For example, with a hash type primary key, you only need to specify
     * the hash attribute. For a hash-and-range type primary key, you must
     * specify <i>both</i> the hash attribute and the range attribute. </li>
     * </ul> </li> <li> <p><i>PutRequest</i> - Perform a <i>PutItem</i>
     * operation on the specified item. The item to be put is identified by
     * an <i>Item</i> subelement: <ul> <li> <p><i>Item</i> - A map of
     * attributes and their values. Each entry in this map consists of an
     * attribute name and an attribute value. Attribute values must not be
     * null; string and binary type attributes must have lengths greater than
     * zero; and set type attributes must not be empty. Requests that contain
     * empty values will be rejected with a <i>ValidationException</i>
     * exception. <p>If you specify any attributes that are part of an index
     * key, then the data types for those attributes must match those of the
     * schema in the table's attribute definition. </li> </ul> </li> </ul>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 25<br/>
     */
    private java.util.Map<String,java.util.List<WriteRequest>> requestItems;

    /**
     * A value that if set to <code>TOTAL</code>, the response includes
     * <i>ConsumedCapacity</i> data for tables and indexes. If set to
     * <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     * for indexes. If set to <code>NONE</code> (the default),
     * <i>ConsumedCapacity</i> is not included in the response.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>INDEXES, TOTAL, NONE
     */
    private String returnConsumedCapacity;

    /**
     * A value that if set to <code>SIZE</code>, the response includes
     * statistics about item collections, if any, that were modified during
     * the operation are returned in the response. If set to
     * <code>NONE</code> (the default), no statistics are returned.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>SIZE, NONE
     */
    private String returnItemCollectionMetrics;

    /**
     * Default constructor for a new BatchWriteItemRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public BatchWriteItemRequest() {}
    
    /**
     * Constructs a new BatchWriteItemRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param requestItems A map of one or more table names and, for each
     * table, a list of operations to be performed (<i>DeleteRequest</i> or
     * <i>PutRequest</i>). Each element in the map consists of the following:
     * <ul> <li> <p><i>DeleteRequest</i> - Perform a <i>DeleteItem</i>
     * operation on the specified item. The item to be deleted is identified
     * by a <i>Key</i> subelement: <ul> <li> <p><i>Key</i> - A map of primary
     * key attribute values that uniquely identify the ! item. Each entry in
     * this map consists of an attribute name and an attribute value. For
     * each primary key, you must provide <i>all</i> of the key attributes.
     * For example, with a hash type primary key, you only need to specify
     * the hash attribute. For a hash-and-range type primary key, you must
     * specify <i>both</i> the hash attribute and the range attribute. </li>
     * </ul> </li> <li> <p><i>PutRequest</i> - Perform a <i>PutItem</i>
     * operation on the specified item. The item to be put is identified by
     * an <i>Item</i> subelement: <ul> <li> <p><i>Item</i> - A map of
     * attributes and their values. Each entry in this map consists of an
     * attribute name and an attribute value. Attribute values must not be
     * null; string and binary type attributes must have lengths greater than
     * zero; and set type attributes must not be empty. Requests that contain
     * empty values will be rejected with a <i>ValidationException</i>
     * exception. <p>If you specify any attributes that are part of an index
     * key, then the data types for those attributes must match those of the
     * schema in the table's attribute definition. </li> </ul> </li> </ul>
     */
    public BatchWriteItemRequest(java.util.Map<String,java.util.List<WriteRequest>> requestItems) {
        setRequestItems(requestItems);
    }

    /**
     * A map of one or more table names and, for each table, a list of
     * operations to be performed (<i>DeleteRequest</i> or
     * <i>PutRequest</i>). Each element in the map consists of the following:
     * <ul> <li> <p><i>DeleteRequest</i> - Perform a <i>DeleteItem</i>
     * operation on the specified item. The item to be deleted is identified
     * by a <i>Key</i> subelement: <ul> <li> <p><i>Key</i> - A map of primary
     * key attribute values that uniquely identify the ! item. Each entry in
     * this map consists of an attribute name and an attribute value. For
     * each primary key, you must provide <i>all</i> of the key attributes.
     * For example, with a hash type primary key, you only need to specify
     * the hash attribute. For a hash-and-range type primary key, you must
     * specify <i>both</i> the hash attribute and the range attribute. </li>
     * </ul> </li> <li> <p><i>PutRequest</i> - Perform a <i>PutItem</i>
     * operation on the specified item. The item to be put is identified by
     * an <i>Item</i> subelement: <ul> <li> <p><i>Item</i> - A map of
     * attributes and their values. Each entry in this map consists of an
     * attribute name and an attribute value. Attribute values must not be
     * null; string and binary type attributes must have lengths greater than
     * zero; and set type attributes must not be empty. Requests that contain
     * empty values will be rejected with a <i>ValidationException</i>
     * exception. <p>If you specify any attributes that are part of an index
     * key, then the data types for those attributes must match those of the
     * schema in the table's attribute definition. </li> </ul> </li> </ul>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 25<br/>
     *
     * @return A map of one or more table names and, for each table, a list of
     *         operations to be performed (<i>DeleteRequest</i> or
     *         <i>PutRequest</i>). Each element in the map consists of the following:
     *         <ul> <li> <p><i>DeleteRequest</i> - Perform a <i>DeleteItem</i>
     *         operation on the specified item. The item to be deleted is identified
     *         by a <i>Key</i> subelement: <ul> <li> <p><i>Key</i> - A map of primary
     *         key attribute values that uniquely identify the ! item. Each entry in
     *         this map consists of an attribute name and an attribute value. For
     *         each primary key, you must provide <i>all</i> of the key attributes.
     *         For example, with a hash type primary key, you only need to specify
     *         the hash attribute. For a hash-and-range type primary key, you must
     *         specify <i>both</i> the hash attribute and the range attribute. </li>
     *         </ul> </li> <li> <p><i>PutRequest</i> - Perform a <i>PutItem</i>
     *         operation on the specified item. The item to be put is identified by
     *         an <i>Item</i> subelement: <ul> <li> <p><i>Item</i> - A map of
     *         attributes and their values. Each entry in this map consists of an
     *         attribute name and an attribute value. Attribute values must not be
     *         null; string and binary type attributes must have lengths greater than
     *         zero; and set type attributes must not be empty. Requests that contain
     *         empty values will be rejected with a <i>ValidationException</i>
     *         exception. <p>If you specify any attributes that are part of an index
     *         key, then the data types for those attributes must match those of the
     *         schema in the table's attribute definition. </li> </ul> </li> </ul>
     */
    public java.util.Map<String,java.util.List<WriteRequest>> getRequestItems() {
        
        return requestItems;
    }
    
    /**
     * A map of one or more table names and, for each table, a list of
     * operations to be performed (<i>DeleteRequest</i> or
     * <i>PutRequest</i>). Each element in the map consists of the following:
     * <ul> <li> <p><i>DeleteRequest</i> - Perform a <i>DeleteItem</i>
     * operation on the specified item. The item to be deleted is identified
     * by a <i>Key</i> subelement: <ul> <li> <p><i>Key</i> - A map of primary
     * key attribute values that uniquely identify the ! item. Each entry in
     * this map consists of an attribute name and an attribute value. For
     * each primary key, you must provide <i>all</i> of the key attributes.
     * For example, with a hash type primary key, you only need to specify
     * the hash attribute. For a hash-and-range type primary key, you must
     * specify <i>both</i> the hash attribute and the range attribute. </li>
     * </ul> </li> <li> <p><i>PutRequest</i> - Perform a <i>PutItem</i>
     * operation on the specified item. The item to be put is identified by
     * an <i>Item</i> subelement: <ul> <li> <p><i>Item</i> - A map of
     * attributes and their values. Each entry in this map consists of an
     * attribute name and an attribute value. Attribute values must not be
     * null; string and binary type attributes must have lengths greater than
     * zero; and set type attributes must not be empty. Requests that contain
     * empty values will be rejected with a <i>ValidationException</i>
     * exception. <p>If you specify any attributes that are part of an index
     * key, then the data types for those attributes must match those of the
     * schema in the table's attribute definition. </li> </ul> </li> </ul>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 25<br/>
     *
     * @param requestItems A map of one or more table names and, for each table, a list of
     *         operations to be performed (<i>DeleteRequest</i> or
     *         <i>PutRequest</i>). Each element in the map consists of the following:
     *         <ul> <li> <p><i>DeleteRequest</i> - Perform a <i>DeleteItem</i>
     *         operation on the specified item. The item to be deleted is identified
     *         by a <i>Key</i> subelement: <ul> <li> <p><i>Key</i> - A map of primary
     *         key attribute values that uniquely identify the ! item. Each entry in
     *         this map consists of an attribute name and an attribute value. For
     *         each primary key, you must provide <i>all</i> of the key attributes.
     *         For example, with a hash type primary key, you only need to specify
     *         the hash attribute. For a hash-and-range type primary key, you must
     *         specify <i>both</i> the hash attribute and the range attribute. </li>
     *         </ul> </li> <li> <p><i>PutRequest</i> - Perform a <i>PutItem</i>
     *         operation on the specified item. The item to be put is identified by
     *         an <i>Item</i> subelement: <ul> <li> <p><i>Item</i> - A map of
     *         attributes and their values. Each entry in this map consists of an
     *         attribute name and an attribute value. Attribute values must not be
     *         null; string and binary type attributes must have lengths greater than
     *         zero; and set type attributes must not be empty. Requests that contain
     *         empty values will be rejected with a <i>ValidationException</i>
     *         exception. <p>If you specify any attributes that are part of an index
     *         key, then the data types for those attributes must match those of the
     *         schema in the table's attribute definition. </li> </ul> </li> </ul>
     */
    public void setRequestItems(java.util.Map<String,java.util.List<WriteRequest>> requestItems) {
        this.requestItems = requestItems;
    }
    
    /**
     * A map of one or more table names and, for each table, a list of
     * operations to be performed (<i>DeleteRequest</i> or
     * <i>PutRequest</i>). Each element in the map consists of the following:
     * <ul> <li> <p><i>DeleteRequest</i> - Perform a <i>DeleteItem</i>
     * operation on the specified item. The item to be deleted is identified
     * by a <i>Key</i> subelement: <ul> <li> <p><i>Key</i> - A map of primary
     * key attribute values that uniquely identify the ! item. Each entry in
     * this map consists of an attribute name and an attribute value. For
     * each primary key, you must provide <i>all</i> of the key attributes.
     * For example, with a hash type primary key, you only need to specify
     * the hash attribute. For a hash-and-range type primary key, you must
     * specify <i>both</i> the hash attribute and the range attribute. </li>
     * </ul> </li> <li> <p><i>PutRequest</i> - Perform a <i>PutItem</i>
     * operation on the specified item. The item to be put is identified by
     * an <i>Item</i> subelement: <ul> <li> <p><i>Item</i> - A map of
     * attributes and their values. Each entry in this map consists of an
     * attribute name and an attribute value. Attribute values must not be
     * null; string and binary type attributes must have lengths greater than
     * zero; and set type attributes must not be empty. Requests that contain
     * empty values will be rejected with a <i>ValidationException</i>
     * exception. <p>If you specify any attributes that are part of an index
     * key, then the data types for those attributes must match those of the
     * schema in the table's attribute definition. </li> </ul> </li> </ul>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 25<br/>
     *
     * @param requestItems A map of one or more table names and, for each table, a list of
     *         operations to be performed (<i>DeleteRequest</i> or
     *         <i>PutRequest</i>). Each element in the map consists of the following:
     *         <ul> <li> <p><i>DeleteRequest</i> - Perform a <i>DeleteItem</i>
     *         operation on the specified item. The item to be deleted is identified
     *         by a <i>Key</i> subelement: <ul> <li> <p><i>Key</i> - A map of primary
     *         key attribute values that uniquely identify the ! item. Each entry in
     *         this map consists of an attribute name and an attribute value. For
     *         each primary key, you must provide <i>all</i> of the key attributes.
     *         For example, with a hash type primary key, you only need to specify
     *         the hash attribute. For a hash-and-range type primary key, you must
     *         specify <i>both</i> the hash attribute and the range attribute. </li>
     *         </ul> </li> <li> <p><i>PutRequest</i> - Perform a <i>PutItem</i>
     *         operation on the specified item. The item to be put is identified by
     *         an <i>Item</i> subelement: <ul> <li> <p><i>Item</i> - A map of
     *         attributes and their values. Each entry in this map consists of an
     *         attribute name and an attribute value. Attribute values must not be
     *         null; string and binary type attributes must have lengths greater than
     *         zero; and set type attributes must not be empty. Requests that contain
     *         empty values will be rejected with a <i>ValidationException</i>
     *         exception. <p>If you specify any attributes that are part of an index
     *         key, then the data types for those attributes must match those of the
     *         schema in the table's attribute definition. </li> </ul> </li> </ul>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public BatchWriteItemRequest withRequestItems(java.util.Map<String,java.util.List<WriteRequest>> requestItems) {
        setRequestItems(requestItems);
        return this;
    }

    /**
     * A map of one or more table names and, for each table, a list of
     * operations to be performed (<i>DeleteRequest</i> or
     * <i>PutRequest</i>). Each element in the map consists of the following:
     * <ul> <li> <p><i>DeleteRequest</i> - Perform a <i>DeleteItem</i>
     * operation on the specified item. The item to be deleted is identified
     * by a <i>Key</i> subelement: <ul> <li> <p><i>Key</i> - A map of primary
     * key attribute values that uniquely identify the ! item. Each entry in
     * this map consists of an attribute name and an attribute value. For
     * each primary key, you must provide <i>all</i> of the key attributes.
     * For example, with a hash type primary key, you only need to specify
     * the hash attribute. For a hash-and-range type primary key, you must
     * specify <i>both</i> the hash attribute and the range attribute. </li>
     * </ul> </li> <li> <p><i>PutRequest</i> - Perform a <i>PutItem</i>
     * operation on the specified item. The item to be put is identified by
     * an <i>Item</i> subelement: <ul> <li> <p><i>Item</i> - A map of
     * attributes and their values. Each entry in this map consists of an
     * attribute name and an attribute value. Attribute values must not be
     * null; string and binary type attributes must have lengths greater than
     * zero; and set type attributes must not be empty. Requests that contain
     * empty values will be rejected with a <i>ValidationException</i>
     * exception. <p>If you specify any attributes that are part of an index
     * key, then the data types for those attributes must match those of the
     * schema in the table's attribute definition. </li> </ul> </li> </ul>
     * <p>
     * The method adds a new key-value pair into RequestItems parameter, and
     * returns a reference to this object so that method calls can be chained
     * together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 25<br/>
     *
     * @param key The key of the entry to be added into RequestItems.
     * @param value The corresponding value of the entry to be added into RequestItems.
     */
    public BatchWriteItemRequest addRequestItemsEntry(String key, java.util.List<WriteRequest> value) {
        if (null == this.requestItems) {
            this.requestItems = new java.util.HashMap<String,java.util.List<WriteRequest>>();
        }
        if (this.requestItems.containsKey(key))
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        this.requestItems.put(key, value);
        return this;
    }

    /**
     * Removes all the entries added into RequestItems.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     */
    public BatchWriteItemRequest clearRequestItemsEntries() {
        this.requestItems = null;
        return this;
    }
    
    /**
     * A value that if set to <code>TOTAL</code>, the response includes
     * <i>ConsumedCapacity</i> data for tables and indexes. If set to
     * <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     * for indexes. If set to <code>NONE</code> (the default),
     * <i>ConsumedCapacity</i> is not included in the response.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>INDEXES, TOTAL, NONE
     *
     * @return A value that if set to <code>TOTAL</code>, the response includes
     *         <i>ConsumedCapacity</i> data for tables and indexes. If set to
     *         <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     *         for indexes. If set to <code>NONE</code> (the default),
     *         <i>ConsumedCapacity</i> is not included in the response.
     *
     * @see ReturnConsumedCapacity
     */
    public String getReturnConsumedCapacity() {
        return returnConsumedCapacity;
    }
    
    /**
     * A value that if set to <code>TOTAL</code>, the response includes
     * <i>ConsumedCapacity</i> data for tables and indexes. If set to
     * <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     * for indexes. If set to <code>NONE</code> (the default),
     * <i>ConsumedCapacity</i> is not included in the response.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>INDEXES, TOTAL, NONE
     *
     * @param returnConsumedCapacity A value that if set to <code>TOTAL</code>, the response includes
     *         <i>ConsumedCapacity</i> data for tables and indexes. If set to
     *         <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     *         for indexes. If set to <code>NONE</code> (the default),
     *         <i>ConsumedCapacity</i> is not included in the response.
     *
     * @see ReturnConsumedCapacity
     */
    public void setReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
    }
    
    /**
     * A value that if set to <code>TOTAL</code>, the response includes
     * <i>ConsumedCapacity</i> data for tables and indexes. If set to
     * <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     * for indexes. If set to <code>NONE</code> (the default),
     * <i>ConsumedCapacity</i> is not included in the response.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>INDEXES, TOTAL, NONE
     *
     * @param returnConsumedCapacity A value that if set to <code>TOTAL</code>, the response includes
     *         <i>ConsumedCapacity</i> data for tables and indexes. If set to
     *         <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     *         for indexes. If set to <code>NONE</code> (the default),
     *         <i>ConsumedCapacity</i> is not included in the response.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see ReturnConsumedCapacity
     */
    public BatchWriteItemRequest withReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
        return this;
    }

    /**
     * A value that if set to <code>TOTAL</code>, the response includes
     * <i>ConsumedCapacity</i> data for tables and indexes. If set to
     * <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     * for indexes. If set to <code>NONE</code> (the default),
     * <i>ConsumedCapacity</i> is not included in the response.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>INDEXES, TOTAL, NONE
     *
     * @param returnConsumedCapacity A value that if set to <code>TOTAL</code>, the response includes
     *         <i>ConsumedCapacity</i> data for tables and indexes. If set to
     *         <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     *         for indexes. If set to <code>NONE</code> (the default),
     *         <i>ConsumedCapacity</i> is not included in the response.
     *
     * @see ReturnConsumedCapacity
     */
    public void setReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
    }
    
    /**
     * A value that if set to <code>TOTAL</code>, the response includes
     * <i>ConsumedCapacity</i> data for tables and indexes. If set to
     * <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     * for indexes. If set to <code>NONE</code> (the default),
     * <i>ConsumedCapacity</i> is not included in the response.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>INDEXES, TOTAL, NONE
     *
     * @param returnConsumedCapacity A value that if set to <code>TOTAL</code>, the response includes
     *         <i>ConsumedCapacity</i> data for tables and indexes. If set to
     *         <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     *         for indexes. If set to <code>NONE</code> (the default),
     *         <i>ConsumedCapacity</i> is not included in the response.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see ReturnConsumedCapacity
     */
    public BatchWriteItemRequest withReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
        return this;
    }

    /**
     * A value that if set to <code>SIZE</code>, the response includes
     * statistics about item collections, if any, that were modified during
     * the operation are returned in the response. If set to
     * <code>NONE</code> (the default), no statistics are returned.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>SIZE, NONE
     *
     * @return A value that if set to <code>SIZE</code>, the response includes
     *         statistics about item collections, if any, that were modified during
     *         the operation are returned in the response. If set to
     *         <code>NONE</code> (the default), no statistics are returned.
     *
     * @see ReturnItemCollectionMetrics
     */
    public String getReturnItemCollectionMetrics() {
        return returnItemCollectionMetrics;
    }
    
    /**
     * A value that if set to <code>SIZE</code>, the response includes
     * statistics about item collections, if any, that were modified during
     * the operation are returned in the response. If set to
     * <code>NONE</code> (the default), no statistics are returned.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>SIZE, NONE
     *
     * @param returnItemCollectionMetrics A value that if set to <code>SIZE</code>, the response includes
     *         statistics about item collections, if any, that were modified during
     *         the operation are returned in the response. If set to
     *         <code>NONE</code> (the default), no statistics are returned.
     *
     * @see ReturnItemCollectionMetrics
     */
    public void setReturnItemCollectionMetrics(String returnItemCollectionMetrics) {
        this.returnItemCollectionMetrics = returnItemCollectionMetrics;
    }
    
    /**
     * A value that if set to <code>SIZE</code>, the response includes
     * statistics about item collections, if any, that were modified during
     * the operation are returned in the response. If set to
     * <code>NONE</code> (the default), no statistics are returned.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>SIZE, NONE
     *
     * @param returnItemCollectionMetrics A value that if set to <code>SIZE</code>, the response includes
     *         statistics about item collections, if any, that were modified during
     *         the operation are returned in the response. If set to
     *         <code>NONE</code> (the default), no statistics are returned.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see ReturnItemCollectionMetrics
     */
    public BatchWriteItemRequest withReturnItemCollectionMetrics(String returnItemCollectionMetrics) {
        this.returnItemCollectionMetrics = returnItemCollectionMetrics;
        return this;
    }

    /**
     * A value that if set to <code>SIZE</code>, the response includes
     * statistics about item collections, if any, that were modified during
     * the operation are returned in the response. If set to
     * <code>NONE</code> (the default), no statistics are returned.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>SIZE, NONE
     *
     * @param returnItemCollectionMetrics A value that if set to <code>SIZE</code>, the response includes
     *         statistics about item collections, if any, that were modified during
     *         the operation are returned in the response. If set to
     *         <code>NONE</code> (the default), no statistics are returned.
     *
     * @see ReturnItemCollectionMetrics
     */
    public void setReturnItemCollectionMetrics(ReturnItemCollectionMetrics returnItemCollectionMetrics) {
        this.returnItemCollectionMetrics = returnItemCollectionMetrics.toString();
    }
    
    /**
     * A value that if set to <code>SIZE</code>, the response includes
     * statistics about item collections, if any, that were modified during
     * the operation are returned in the response. If set to
     * <code>NONE</code> (the default), no statistics are returned.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>SIZE, NONE
     *
     * @param returnItemCollectionMetrics A value that if set to <code>SIZE</code>, the response includes
     *         statistics about item collections, if any, that were modified during
     *         the operation are returned in the response. If set to
     *         <code>NONE</code> (the default), no statistics are returned.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see ReturnItemCollectionMetrics
     */
    public BatchWriteItemRequest withReturnItemCollectionMetrics(ReturnItemCollectionMetrics returnItemCollectionMetrics) {
        this.returnItemCollectionMetrics = returnItemCollectionMetrics.toString();
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRequestItems() != null) sb.append("RequestItems: " + getRequestItems() + ",");
        if (getReturnConsumedCapacity() != null) sb.append("ReturnConsumedCapacity: " + getReturnConsumedCapacity() + ",");
        if (getReturnItemCollectionMetrics() != null) sb.append("ReturnItemCollectionMetrics: " + getReturnItemCollectionMetrics() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getRequestItems() == null) ? 0 : getRequestItems().hashCode()); 
        hashCode = prime * hashCode + ((getReturnConsumedCapacity() == null) ? 0 : getReturnConsumedCapacity().hashCode()); 
        hashCode = prime * hashCode + ((getReturnItemCollectionMetrics() == null) ? 0 : getReturnItemCollectionMetrics().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof BatchWriteItemRequest == false) return false;
        BatchWriteItemRequest other = (BatchWriteItemRequest)obj;
        
        if (other.getRequestItems() == null ^ this.getRequestItems() == null) return false;
        if (other.getRequestItems() != null && other.getRequestItems().equals(this.getRequestItems()) == false) return false; 
        if (other.getReturnConsumedCapacity() == null ^ this.getReturnConsumedCapacity() == null) return false;
        if (other.getReturnConsumedCapacity() != null && other.getReturnConsumedCapacity().equals(this.getReturnConsumedCapacity()) == false) return false; 
        if (other.getReturnItemCollectionMetrics() == null ^ this.getReturnItemCollectionMetrics() == null) return false;
        if (other.getReturnItemCollectionMetrics() != null && other.getReturnItemCollectionMetrics().equals(this.getReturnItemCollectionMetrics()) == false) return false; 
        return true;
    }
    
}
    