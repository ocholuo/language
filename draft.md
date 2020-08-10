
**CloudTrail**

An event in CloudTrail is the record of an activity in an AWS account. 
- This activity can be an action taken by a user, role, or service that is monitorable by CloudTrail. 
- CloudTrail events provide a history of both API and non-API account activity made through the AWS Management Console, AWS SDKs, command line tools, and other AWS services. 
- There are two types of events that can be logged in CloudTrail: 
  - management events and data events. 
  - By default, trails log management events
  - Both use the same CloudTrail JSON log format. 


Management Events
- information about **management operations** performed on resources in AWS account. These are also known as control plane operations. 
- Example management events include:
  - Configuring security 
    - IAM `AttachRolePolicy` API operations...
  - Registering devices
    - Amazon EC2 `CreateDefaultVpc` API operations...
  - Configuring rules for routing data
    - Amazon EC2 `CreateSubnet` API operations...
  - Setting up logging
    - AWS CloudTrail `CreateTrail` API operations...
- Management events can also include non-API events that occur in your account. For example, when a user signs in to your account, CloudTrail logs the ConsoleLogin event. 


Data Events
- information about the **resource operations** performed on or in a resource. These are also known as data plane operations. 
- Data events are often high-volume activities. 
- The following two data types are recorded:
  - **S3** object-level API activity
    - `GetObject`, `DeleteObject`, and `PutObject` API operations...
  - **Lambda function** execution activity 
    - the Invoke API...
- Data events are disabled by default when create a trail
  - must explicitly add to a trail the supported resources or resource types for which you want to collect activity. 

Insights Events
- capture unusual activity in your AWS account. 
- If you have Insights events enabled, and CloudTrail detects unusual activity, 
- Insights events are logged to a different folder or prefix in the destination S3 bucket for your trail. 
- You can also see the type of insight and the incident time period when you view Insights events on the CloudTrail console. 
- Insights events provide relevant information, such as the associated API, incident time, and statistics, that help you understand and act on unusual activity. 
- Insights events are logged only when CloudTrail detects changes in your account's API usage that differ significantly from the account's typical usage patterns. 
- Examples of activity that might generate Insights events include:
  - Your account typically logs no more than 20 Amazon S3 `deleteBucket` API calls per minute, but your account starts to log an average of 100 `deleteBucket` API calls per minute. An Insights event is logged at the start of the unusual activity, and another Insights event is logged to mark the end of the unusual activity.
  - Your account typically logs 20 calls per minute to the Amazon EC2 `AuthorizeSecurityGroupIngress` API, but your account starts to log zero calls to `AuthorizeSecurityGroupIngress`. An Insights event is logged at the start of the unusual activity, and ten minutes later, when the unusual activity ends, another Insights event is logged to mark the end of the unusual activity.
- Insights events are disabled by default when you create a trail. 
  - must explicitly enable Insights event collection on a new or existing trail. 
  

CloudTrail Event History
- provides a viewable, searchable, and downloadable record of the past 90 days of CloudTrail events. 
- gain visibility into actions taken in your AWS account in the AWS Management Console, AWS SDKs, command line tools, and other AWS services. 
- You can customize your view of event history in the CloudTrail console by selecting which columns are displayed. 


Trails
- a configuration that enables delivery of CloudTrail events to an `S3 bucket, CloudWatch Logs, and CloudWatch Events`. 
- You can use a trail to filter the CloudTrail events you want delivered, encrypt your CloudTrail event log files with an AWS KMS key, and set up Amazon SNS notifications for log file delivery. 


Organization Trails
- a configuration that enables delivery of CloudTrail events in the `master account and all member accounts in an organization` to the same Amazon S3 bucket, CloudWatch Logs, and CloudWatch Events. 
- Creating an organization trail helps you define a uniform event logging strategy for your organization.
- When you create an organization trail, a trail with the name that you give it will be created in every AWS account that belongs to your organization. 
- Users with CloudTrail permissions in member accounts will be able to see this trail (including the trail ARN) when they log into the AWS CloudTrail console from their AWS accounts, or when they run AWS CLI commands such as describe-trails (although member accounts must use the ARN for the organization trail, and not the name, when using the AWS CLI). 
- However, users in member accounts will not have sufficient permissions to delete the organization trail, turn logging on or off, change what types of events are logged, or otherwise alter the organization trail in any way.


**VPC Flow Log**: 
- capture metadata IP traffic going to and from network interfaces in VPC
- With VPC Flow Logs, to attach VPC monitoring
  - attache VPC Flow Log to a **VPC**
    - will monitor every network interface inside the VPC. 
    - monitors IP data going in/out of the interface.
  - attache VPC Flow Log to a **network interface**
    - solely monitor that network interface.
    - monitors IP data going in/out of the interface.
  - attache VPC Flow Log to a **specific subnet within a VPC**
    - monitors every network interface inside the subnet. 
    - monitors IP data going in/out of the interface.
  - **not on an Availability Zone**


**CloudWatch**: 
- a repository for metric data
- capture metadata IP traffic going to and from network interfaces in your VPC
- produce metrics, and these are time-ordered sets of data.
- can be configured with **alarms** to take **actions**
  - these actions can be used to trigger services like Auto Scaling groups.
  - a matched rule can take action on a target
- **CloudWatch Events** 
  - becomes aware of operational changes as they occur and if a rule is matched, can take corrective action as necessary
  - CloudWatch Events already has access to AWS API events; it only need CloudTrail enabled when services aren't directly supported.
  - 2 options when creating a rule.
    - invoke a target by its event patterns 
    - invoke a target by a schedule.
- a service that stores metrics, much like a repository stores files.
- CloudWatch log group:
  - export and set streams into other AWS services.
  - where you can set the retention period.
  - where you set a metric filter.
-  Metrics can be configured with alarms that can take action. 
-  key components of a CloudWatch alarm
   -  thresholds: static / anomaly detection.
   -  metrics: measured data points over time.
   -  action: action can produce an email or even work in conjunction with Auto Scaling groups.
   -  period: 
      -  period is related to its threshold.
      -  the length of time in which a threshold is surpassed before an alarm is generated.
- 

**CloudTrail**: service that records API activity in an AWS account


a service that monitors AWS resources and applications


**Execution / permission roles** give a service the permissions to write logs to CloudWatch Logs.


CloudTrail can save event history for up to **90** days.


Elastic Beanstalk
- used if your code is using a supported runtime (Ruby, Python, etc.)
- when needing minimal to no admin overhead
- key architecture components in Elastic Beanstalk.
  - **an application**
    - The base entity of Elastic Beanstalk is an application. 
    - An Elastic Beanstalk's application can be thought of as a `container`.
  - **environment**
    - work environment or web server environment.
    - allows for quick environment deployment and management of an application
    - an application can contain zero to multiple environments
    - Each environment has a **different URL**
      - can use each URL for A/B testing to see which application version is better for users.
    - Environments live in an application container and it references a specific application version 
    - `application container >> environment >> a single application version`
  - **An application version**
    - a distinct version of an app's code that's packaged into a source bundle.

**CMKs Customer Master Key**
- AWS Managed CMK
  - the default key type for most AWS services
  - can only be directly used by the associated service 
  - is formatted as `aws/service-name`
- Customer managed CMKs 
  - allow customer to granularly manage permissions
  - configure a key: permissions, enable/disable, and rotation.

**KMS** 
- uses Base64 encoded text for operations that receive or send data.
- Key policies allow/deny access to Customer Managed Keys
  - AWS Managed key policies can't be adjusted, while Customer Managed KMS do.
- a regional specific service
  - keys can only be used in the region in which they were created.

**DEK Data Encryption Key**
- for encrypting data larger than 4 KB.
- generated using a CMK.
- issued upon request.



opswork
- main components of the OpsWorks service
  - layer:
    - part of a stack
    - can be compared to tiers within an application. 
    - (stacks >> layer (created by recipes) >> instances)
  - recipes
    - defined in a single layer basis.
  - stacks
    - an isolated unit of managed infrastructure.
  - apps
    - Apps are deployed to layers from a source code repository of S3.
  - instances
    - Instances are associated with a stack's layer. 
    - (stacks >> layer >> instances)
- Chef 12 stack is capable of managing both Linux and Windows-based operating systems.
- Cookbooks (aka recipes) come from a repository.
- Layers depend on Chef recipes to handle tasks such as installing packages on instances, deploying apps and running scripts.
- require IAM permissions to interact with different components of AWS.

----

## list

**CloudWatch logs accepts connections from:**

AWS services.

API streams from a custom application.

CloudWatch agents.




---

## question



Hey this is linda speaking, may i talk with jon,

i supposed to meet him at there, but he is not answering the phone, i just have a quit question to ask he.

group: he told me that he work for security.

can you tell me the address

ok can you tell me the company name so i can google it

is it a building, what inside it. type oh facility.

can you tell me when the **shift changing** is so maybe i can call later.








.