

## note


## AWS Storage Gateway
customers can back up and restore their on-premises applications to the cloud using Storage Gateway.
- File Gateway, Tape Gateway, and Volume Gateway

AWS Storage Gateway

Customers use Storage Gateway to protect their on-premises applications and to reduce backup infrastructure and administration costs.
- They use Storage Gateway to back up files, applications, databases, and volumes to **S3, S3 Glacier, S3 Glacier Deep Archive, and EBS**, through files, volumes, snapshots, and virtual tapes in AWS.
- Some customers use Storage Gateway to seamlessly complement their existing storage infrastructure to offload and/or expand their on-premises storage capacity.
- Since there is no hardware procurement needed, these deployments are faster and shorter.

Using Storage Gateway, customers are able to seamlessly connect on-premises applications to AWS to leverage cloud storage scalability, reliability, durability, and economics.

Storage Gateway supports standard storage protocols such as **NFS, SMB, iSCSI, and iSCSI-VTL**.

Minimal changes are required to existing applications, and it’s an ideal solution for customers that must support a hybrid storage environment that bridges both on-premises and cloud. Storage Gateway uses a highly optimized data transfer mechanism, bandwidth management, and automated network resilience for efficient data transfer. All data is encrypted in-transit and at-rest in the cloud.

![Move-on-premises-backups-to-the-cloud-with-File-Gateway-Tape-Gateway-or-Volume-Gateway](/assets/Move-on-premises-backups-to-the-cloud-with-File-Gateway-Tape-Gateway-or-Volume-Gateway.jfif)

Storage Gateway provides three types of storage interfaces to back up customers’ on-premises applications:
- File Gateway
  - store and access objects in S3 from file-based applications with local caching.
  - Client access is provided via Network File System (NFS) and Server Message Block (SMB)
  - each file stored as an object in S3 with one-to-one mapping.
  - Objects written through File Gateway can be directly accessed in S3.
- Tape Gateway
  - drop-in replacement for physical tape infrastructure backed by cloud storage with local caching for low-latency data access.
  - provides your backup application with an iSCSI virtual tape library (VTL) interface,
  - consisting of a virtual media changer, virtual tape drives, and virtual tapes.
  - Virtual tapes are stored in S3 and can be archived to S3 Glacier or S3 Glacier Deep Archive.
- Volume Gateway
  - provides on-premises block storage over iSCSI,
  - with local caching, Amazon EBS snapshots, and clones
  - Data on the volumes is stored in Amazon S3
  - can take point in time copies of volumes which are stored in AWS as Amazon EBS snapshots.
  - can also take copies of volumes and manage their retention using AWS Backup.
  - can restore EBS snapshots to a Volume Gateway volume or an EBS volume.


### On-Premises backup and restore using

### File Gateway

![Move-databases-and-file-backups-into-the-cloud-and-free-up-on-premises-storage-capacity-with-File-Gateway](/assets/Move-databases-and-file-backups-into-the-cloud-and-free-up-on-premises-storage-capacity-with-File-Gateway.jfif)

- File Gateway presents a file interface
- **Move databases and file** backups into the cloud
  - to store database and application files as durable objects in S3 using NFS and SMB file protocol.
    - backed up files in S3 for applications, such as SAP, SQL Server, and Oracle.
  - free up on-premises storage capacity / EC2 with File Gateway
    - reduce their on-premises backup storage footprint.
  - Databases and applications are often backed up directly to a file server on premises.

- File Gateway includes a **local cache**
  - to temporarily hold changed data that must be transferred to AWS
  - to locally cache data for low-latency read access.

- perform backups to your file share using File Gateway
  - data is stored locally first and then asynchronously uploaded to your Amazon S3 bucket.

- seamlessly access your backed-up database and application files in S3 via NFS and SMB.
  - backup files are stored as objects in the S3 buckets
  - with one-to-one file to object mapping.  
  - Once the data is in S3, you can optionally use S3 Lifecycle policies to automatically archive data to lower-cost storage classes
    - S3 Glacier is well suited for data archiving, and Amazon S3 Glacier Deep Archive is suitable for long-term retention of data that is infrequently accessed.
    S3 Glacier offers three choices for access to archives, from a few minutes to many hours – depending on your retrieval needs.
    S3 Glacier Deep Archive offers two access choices varying from 12 to 48 hours.
  - must manually restore the object back to S3 Standard before it can be accessed through a File Gateway.
    - To automate restoring the archived files, use CloudWatch and Lambda function to trigger a restore request to Amazon S3.

File Gateway can be used with S3 Object Lock to enable write-once-read-many (WORM) file-based systems to store and access objects in Amazon S3 by using Object Lock’s Compliance or Governance modes.
- Any modifications such as file edits, deletes, or renames from the gateway’s NFS or SMB clients are stored as new versions of the object, without overwriting or deleting previous versions.
- This leaves the original, locked version of the object unchanged, enabling you to enforce policies as an added layer of data protection or for regulatory compliance.


### Tape Gateway

![Replace-physical-tape-infrastructure-with-virtual-tape-workflows-using-Tape-Gateway](/assets/Replace-physical-tape-infrastructure-with-virtual-tape-workflows-using-Tape-Gateway_8k0jmn1gh.jfif)

![Replace-physical-tape-infrastructure-without-changing-existing-backup-or-archiving-workflows-using-Tape-Gateway](/assets/Replace-physical-tape-infrastructure-without-changing-existing-backup-or-archiving-workflows-using-Tape-Gateway.jfif)

- **Replace physical tape infrastructure** with virtual tape workflows using Tape Gateway
  - eliminate the use of physical tape, offsite tape warehousing, and maintenance associated with on-premises physical tape infrastructure.
- easy drop-in replacement for physical tape infrastructure.
- to have a limitless collection of virtual tapes
  - without requiring changes to existing backup software or archiving workflows.
  - no longer have to deal with the hassles and challenges associated with physical media – tape loading and unloading, tape degradation, tape media migration, offsite tape vaulting, and magnetic tape library management.
- Tape Gateway serves as a virtual tape library (VTL)
- supports key backup applications.
- Tape Gateway presents an iSCSI interface
  - emulates a magnetic tape library that can be integrated into your backup or archive framework.

- back up data to AWS, the virtual tapes are stored in a virtual tape library in S3 bucket.
  - Active virtual tapes are stored in Amazon S3 Standard.
  - You then use your backup application to move the virtual tape from the virtual tape library to the virtual tape shelf by either exporting or ejecting the tapes.
  - Doing so archives your tapes to S3 Glacier or S3 Glacier Deep Archive.
  - AWS regularly performs fixity checks on a regular basis to confirm that your data can be read, and no errors have been introduced.

- As virtual tapes get used, Tape Gateway automatically creates new virtual tapes to maintain a minimum number of available tapes.
  - Tape Gateway then makes these new tapes available for import by the backup application so that your backup jobs can run without interruption.

- Access to virtual tapes in your virtual tape library is instantaneous.
  - If the virtual tape containing your data is archived, to restore data from tape, must first retrieve the virtual tape using the AWS Management Console or API.
  - To retrieve the virtual tape
    - select the virtual tape
    - choose the Tape Gateway into which you want the virtual tape to be loaded.
    - You can retrieve a tape archived in S3 Glacier and S3 Glacier Deep Archive to the virtual tape library in S3, typically within 3-5 hours or 12 hours respectively.
    - Once the virtual tape is available in the virtual tape library,
    - use your backup application to use the virtual tape to restore data.

- backup and archives are **compressed** and stored durably in S3.
- data is encrypted at rest using Amazon S3-managed encryption keys (S3-SSE) or your own AWS Key Management Service (AWS KMS) keys.


### Volume Gateway > `application`

![Enable-faster-application-recovery-in-cloud-or-on-premises-using-Volume-Gateway](/assets/Enable-faster-application-recovery-in-cloud-or-on-premises-using-Volume-Gateway.jfif)

![Enable-faster-application-recovery-on-premises-or-in-cloud-with-Amazon-EBS-Snapshots-and-Volume-Gateway](/assets/Enable-faster-application-recovery-on-premises-or-in-cloud-with-Amazon-EBS-Snapshots-and-Volume-Gateway.jfif)

- Enable **faster application recovery** in-cloud or on-premises
- Volume Gateway presents cloud-backed storage volumes to your on-premises application using iSCSI.
  - On-premises systems mount the iSCSI volumes
  - applications interact with the volumes as normal block storage.
- Data written to these volumes is compressed and can be asynchronously backed up as point-in-time snapshots of your volumes and stored in the cloud as EBS Snapshots.
- ideal for
  - backup and restore of application data
    - as the point-in-time snapshots are securely stored in S3.
  - to back up their on-premises virtual machines (VM) and databases
    - as it provides for fast volume recovery for applications, such as Oracle, SQL Server, PostgreSQL, and many more.
  - for disaster recovery and for protection against ransomware.
    - For example
    - applications infected with ransomware, can quickly restore to a previous application state before the infection.
    - the volume snapshots can be restored as EBS volumes on EC2 or as volumes on Volume Gateway, which are then presented as iSCSI volumes.
- Volume Gateway supports the following volume modes:
  - Cached Volumes: All data is stored in Amazon S3, and your frequently accessed data is cached locally on-premises. The Cached Volume configuration provides substantial cost savings on on-premises storage by minimizing the need to scale your storage on-premises while retaining low-latency access to your frequently accessed data.
  - Stored Volumes: You store your data on-premises and asynchronously make point-in-time snapshots of this data to Amazon S3. The Stored Volume configuration is ideally suited for low-latency access to your dataset as Volume Gateway provides durable and inexpensive off-site backups that you can recover locally or from Amazon EC2.
  - Enable faster application recovery on-premises or in-cloud with Amazon EBS Snapshots and Volume Gateway

For Cached Volumes, where your volume data is already stored in Amazon S3, you use EBS snapshots to preserve versions of your data. Using this approach, you can revert to a prior version when required or repurpose a point-in-time version as a new volume. You can initiate snapshots on a scheduled or ad hoc basis. When taking a new snapshot, only the data that has changed since your last snapshot is stored. If you have a volume with 100 GB of data, but only 5 GB of data have changed since your last snapshot, only the 5 additional GB of snapshot data is stored in Amazon S3. When you delete a snapshot, only the data not needed for any other snapshot is removed.

For Cached Volumes, the gateway gives you the ability to clone volumes from the most recent recovery point. Cloning of a volume does not require prior creation of EBS snapshots. Cloning enables faster recovery times as it is faster to create and access clones as Volume Gateway presents the volume instantly and copies data from the initial volume in the background. It is still recommended that EBS snapshots be used for backup and recovery purposes as it provides you more specific points in time for recovery purposes. Look at this tutorial to see how to use snapshots and clones with Volume Gateway to recover your volume data on-premises or in-cloud.

For Stored Volumes, where your volume data is stored on-premises, snapshots provide durable, off-site backups in Amazon S3. If you must recover a backup, you can create a new volume from a snapshot. You can also use a snapshot of your volume as the starting point for a new Amazon EBS volume, which you can then attach to an Amazon EC2 instance. Additionally, you can use AWS Backup to control scheduling and managing retention of these snapshots.

Using AWS Backup with Volume Gateway simplifies and centralizes backup management, thus reducing operational burden and making it easier to meet compliance requirements across all your AWS resources. AWS Backup allows you to set customizable scheduled backup policies that meet your backup requirements. Using AWS Backup, you can set backup retention and expiration rules so you no longer need to develop custom scripts or manually manage the point-in-time backups of your Volume Gateway volumes. Finally, you can manage and monitor backups across multiple Volume Gateways, and other AWS resources such as EBS volumes and Amazon RDS databases, from a central view.

TransferWise, a global financial technology company, has been using Volume Gateway to back up databases, which in turn creates EBS Snapshots that are managed by AWS Backup. Click here to watch how STEMCELL Technologies uses Volume Gateway to backup on-premises Oracle databases.







---



## question

A company requires a durable backup storage solution for its on-premises database servers while ensuring on-premises applications maintain access to these backups for quick recovery. The company will use AWS storage services as the destination for these backups. design a solution with minimal operational overhead. Which solution should the solutions architect implement?

A. Deploy an AWS Storage Gateway file gateway on-premises and associate it with an Amazon S3 bucket.
B. Back up the databases to an AWS Storage Gateway volume gateway and access it using the Amazon S3 API.
C. Transfer the database backup files to an Amazon Elastic Block Store (Amazon EBS) volume attached to an Amazon EC2 instance.
D. Back up the database directly to an AWS Snowball device and uss lifecycle rules to move the data to Amazon S3 Glacier Deep Archive.


A solutions architect is moving the static content from a public website hosted on EC2 instances to an S3 bucket. An CloudFront distribution will be used to deliver the static assets. The security group used by the EC2 instances restricts access to a limited set of IP ranges. Access to the static content should be similarly restricted.
Which combination of steps will meet these requirements? (Choose two.)

A. Create an origin access identity (OAI) and associate it with the distribution. Change the permissions in the bucket policy so that only the OAI can read the objects.
B. Create an AWS WAF web ACL that includes the same IP restrictions that exist in the EC2 security group. Associate this new web ACL with the CloudFront distribution.

C. Create a new security group that includes the same IP restrictions that exist in the current EC2 security group. Associate this new security group with the CloudFront distribution.
D. Create a new security group that includes the same IP restrictions that exist in the current EC2 security group. Associate this new security group with the S3 bucket hosting the static content.
-   S3 cannot be in a ASG
E. Create a new IAM role and associate the role with the distribution. Change the permissions either on the S3 bucket or on the files within the S3 bucket so that only the newly created IAM role has read and download permissions.
























.
