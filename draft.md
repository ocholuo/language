

[toc]

---


## s3

Virtual Hosted Style URL style
- `https://my-bucket.s3.us-west-2.amazonaws.com/fastpuppy.csv`


Cost of retrieval from Glacier:
- `Expedited` retrievals: quickly access data stored in the S3 Glacier storage class when occasional urgent requests for a subset of archives are required, but at the highest cost. 
- `Standard` retrieval: access any of archived objects within several hours, faster than bulk (averaging around 12 hours) but more expensive. 
- `Bulk` retrievals: lowest-cost retrieval option in Amazon S3 Glacier, retrieve large amounts, even petabytes, of data inexpensively.



per account can have 100 S3 buckets
the minimum file size that I can store on S3: 0 bytes


availability 
S3-OneZone-IA: 99.50%
S3: 99.99%



- `Signed URLs` and `Signed Cookies` are different ways to ensure that users attempting access to files in an S3 bucket can be authorised. 
- both require the creation of an application and policy to generate and control these items. 
- An `Origin Access Identity` is a virtual user identity that is used to give the CloudFront distribution permission to fetch a private object from an S3 bucket. 
- `Public S3 buckets` should never be used unless you are using the bucket to host a public website and therefore this is an incorrect option.


S3 has eventual consistency for `overwrite PUTS and DELETES` HTTP Methods

S3 has `Read After Write Consistency` consistency model for PUTS of new objects

---

## ec2


AWS originally used a modified version of the `Xen` Hypervisor to host EC2. In 2017, AWS began rolling out their own Hypervisor `Nitro`


`Spread Placement Groups` 
- can be deployed across AZs 
- they spread the instances further apart. 

`Cluster Placement Groups` 
- can only exist in one AZe 
- focused on keeping instances together

help you manage your Amazon EC2 instances, you can assign your own metadata in the form of `tag`


Standard Reserved Instances cannot be moved between regions. 
- You can choose if a Reserved Instance applies to either a specific Availability Zone, or an Entire Region, but you cannot change the region

---

## ebs

create a snapshot: `aws ec2 create-snapshot` 
create a image: `aws ec2 create-image`

 
SR-IOV (Single Root I/O Virtualization): 
- a feature of Enhanced Networking 
- Enhanced Networking: to provide higher networking performance. 
- On a normal EC2 instance, multiple EC2 instances may share a single physical network interface on the EC2 Host. 
- SR-IOV effectively dedicates the interface to a single instance, and bypasses parts of the Hypervisor, allowing for better performance


delete:
1. **cannot delete a snapshot of an EBS Volume that is used as the root device of a registered AMI**:
   - If the original snapshot was deleted, then the AMI would not be able to use it as the basis to create new instances. 
   - AWS protects you from accidentally deleting the EBS Snapshot. 
   - To delete an EBS Snapshot attached to a registered AMI, first remove the AMI, then the snapshot can be deleted

2. **If an Amazon EBS volume is attached as an additional disk (not the root volume), can I detach it without stopping the instance?**
    - Since the additional disk does not contain the operating system, you can detach it in the EC2 Console while the instance is running. However, any data on that drive would become inaccessible, and possibly cause problems for the EC2 instance
3. can control whether the EBS root volume is deleted when associated instance is terminated. 
   - if terminated an EC2 instance, the EBS root volume persist, Unless 'Delete on Termination' is not unchecked for the volume
   - The default delete-on-termination behaviour depends on whether the volume is a root volume, or an additional volume. 
   - By default, the `DeleteOnTermination` attribute for root volumes is set to 'true.' However, this attribute may be changed at launch by using either the AWS Console or the command line. 
   - For an instance that is already running, the `DeleteOnTermination` attribute must be changed using the CLI.


service used to run a general Windows File Server with minimal overhead
- Amazon `FSx for Windows` File Server 
  - fully managed 
  - native Microsoft Windows file system 
  - easily move your Windows-based applications that require shared file storage to AWS. 

EBS 
  - uses Block-based storage
  - the data is stored on a virtual disk managed by the Operating System. 

- EBS Multi Attach 
  - allows to attach a volume to up to 16 instances, 
  - but would have issues across multiple availability zones, and could not use NTFS natively. 

- EFS 
  - uses the NFS protocol, not for Windows. 
  - File-based storage
  - underlying filesystem is managed by AWS


- S3
  - object-based storage,
  - not suitable as the backend for a file server
  - where files are kept in a flat structure


EBS types, HDD based volumes will always be less expensive than SSD types. 

---

## database

`Redshift` most suitable for online analytics processing, OLAP.


chargeable DynamoDB features: 
- charge for provisioning `read and write capacity` and the `storage of data within DynamoDB`
- no charge for the transfer of data into DynamoDB within a single region (cross regions will be charged at both ends of the transfer.)
- no charge for the  number of tables create in DynamoDB, providing the RCU and WCU are set to 0, however in practice you cannot set this to anything less than 1 so there always be a nominal fee associated with each table.


add a rule to an RDS DB security group
- the RDS instance port number is automatically applied to the RDS DB Security Group.





---

**need to be securely stored for a period of 7 years. In some rare cases, you may need to retrieve this data within 24 hours of a claim being lodged.**
Glacier
The recovery rate is a key decider. The record shortage must be; safe, durable, low cost, and the recovery can be slow. All features of Glacier.



**allows users to have secure access to private files located in S3?**
CloudFront Origin Access Identity
CloudFront Signed URLs
CloudFront Signed Cookies 

- `Signed URLs` and `Signed Cookies` are different ways to ensure that users attempting access to files in an S3 bucket can be authorised. 
- both require the creation of an application and policy to generate and control these items. 
- An `Origin Access Identity` is a virtual user identity that is used to give the CloudFront distribution permission to fetch a private object from an S3 bucket. 
- `Public S3 buckets` should never be used unless you are using the bucket to host a public website and therefore this is an incorrect option.




**As the information base grows they use CloudFormation to spin up another stack made up of an S3 bucket and supporting compute instances. The trigger for creating a new stack is when the PUT rate approaches 100 PUTs per second. The problem is that as the business grows that number of buckets is growing into the hundreds and will soon be in the thousands. You have been asked what can be done to reduce the number of buckets without changing the basic architecture.**
Change the trigger level to around 3000 as S3 can now accommodate much higher PUT and GET levels.

- Until 2018 there was a hard limit on S3 puts of 100 PUTs per second. To achieve this care needed to be taken with the structure of the name Key to ensure parallel processing. 
- As of July 2018 the limit was raised to 3500 and the `need for the Key design was basically eliminated`. 
- Disk IOPS and account limit is not the issue with the problem.



**hosting a MySQL database on the root volume of an EC2 instance. The database is using a large number of IOPS, and you need to increase the number of IOPS available to it**
Add 4 additional EBS SSD volumes and create a RAID 10 using these volumes.


**Amazon RDS Provisioned IOPS storage with a Microsoft SQL Server database engine, what is the maximum size RDS volume you can have by default**
16TB









.