### Bandhu – A Social Distancing App
#### The problem statement:
COVID-19 spread has changed day-to-day life in unprecedented ways. Organizations are in the process of accommodating to the new normal and eventually must get the workplace ready and suitable for the same. A system that would facilitate and manage social distancing in the office premises and enable a contact tracing facility that the administration can implement and control - is the need of the hour.
#### The solution:
###### Enable Social distancing by, 
- Alerting the User if his/her vicinity is getting crowded
- Seat allocation plan enables the Admin/Manager to maintain safe distance among associates
- Providing the Admin an alert when the workforce reported to work in the premise exceeds a certain percentage
###### Provide Contact tracing by,
- Providing a system that can trace the contacts of an infected associate, so that organization admin can alert the contacts (conforming to the office privacy policies) to quarantine themselves in order to avoid a potential spread.

#### Solution Features:
###### Provide Associate an application that will enable them to,
- Get alerts if their location/spot is getting over crowded
- Get notification about their seat allocation at office

###### Provide Admin an application that will enable them to,
- Seat allocation can be managed keeping social distancing as the primary concern.
- Report the percentage of workforce reported to work on that day
- Contact tracing reports for a user for the past 15 days - Enables organization admin to satisfy the responsibility of informing associates on any possible exposure that they have had in the office environment

###### The application can be extrapolated to be used within any office premises and thus provide greater control. 

#### Potential Features that could be incorporated/integrated/enhanced:
- Using this architecture as the base, the following features can also be implemented,
- Admin view – Regulating social distancing in work place by alerting if a spot gets overcrowded
- Enabling any organization to report on the statuses of campuses in terms of staff presence, staff health (Thermal Reading ), social distancing characteristics.
- Create intuitive and printable reports for Admin as we capture user and location data into our system
- Admin view to capture and track the COVID cases, seat allocation strategy can be created
- Parameters like number of floors, allowed workforce% in the premise, etc. can be configured
- Expandable to iOS devices as well

Architecture:


###### Technology:
- Android Studio 3.6.3
- Google Nearby API – Device communication
- Google Play Services
- Oracle 12.0.1
- Swagger 2.0
- Spring Boot REST Services
- RETROFIT REST Client 2.x
- Tomcat 8.0
- MPAndroidChart
###### Open source or proprietary software used (if applicable) 
##### Open source:
- Android Studio 3.6.3 - IDE
- Google Nearby API – Device communication
- Google Play Services
- Spring Boot REST Services
- RETROFIT REST Client 2.x

#### Features of the App – the cool factors:
##### The Distance Factor 
- We performed the following feasibility studies to identify a technology that would work within a closed and well-managed premise. 
- Global Position Systems – most suited for outdoor long-distance tracking
- Bluetooth – signal strengths proved to be a poor indicator for proximity sensing
- Wi-Fi – devices should be on the same network, again proximity sensing based on signal strength did not play out well
- Received Signal Strength Indication (RSSI) - an estimated measurement of how good a device can hear, detect and receive signals from any access point or from a specific router had the issue of signal interference

- After much analysis and deliberation Google NearBy Messaging API  was narrowed down to implement this App because of the following comprehensive features,
The Nearby Messages API is a publish-subscribe API that lets the user pass small binary payloads between internet-connected Android and iOS devices. This API uses a combination of Bluetooth, Bluetooth Low Energy, Wi-Fi and near-ultrasonic audio to communicate a unique-in-time pairing code between devices. The server facilitates message exchange between devices that detect the same pairing code. When a device detects a pairing code from a nearby device, it sends the pairing code to the Nearby Messages server for validation, and to check whether there are any messages to deliver for the application’s current set of subscriptions.
###### Planning and management for Admin 
- Using the data collected and demonstrated in this App, Admin team will be able to plan and arrange for food, sanitizers, masks, etc. They will also be able to plan and engage the respective support staff like housekeeping and security as required. They will be able to save considerable time for planning. 
- Admin will also be able to perform seat allocation in an hour rather a week to coordinate with all managers to get the data and make the plan. 
- When an infected associate is identified, the organization admin will be able to respond immediately and inform the other potential contacts about the possible exposure that they might have had in the office environment 

##### Intuitive Demonstration of Reports and Charts
- The Near By devices are captured and displayed on the User screen.
- Percentage of workforce showed up to work in the premise for that day is captured and reported. This view also provides the categorization of associates present based on their roles as a pie chart.
- A pictorial representation of the floor plan, the allocated/unallocated seats is available. 
- Contact tracing for a user is provided in the form of a bar chart providing details of associates who were in close proximity of that user. This can strike a clear idea to the admin whom they need to alert.

#### This is not ArogyaSethu because
- ArogyaSethu app while being mandated by the government has a widespread use across the country.  
- The privacy issues attached to the app will restrict local facilities / admin to have any sort of control over looking up data in the app and or determining exposure within their office. 
- Also reporting the Covid+ cases in ArogyaSethu app is only an optional feature and doesn’t work when data isn’t reported. 
- This application on the other hand provides the full control to the associate and the admin in ensuring social distancing guidelines are followed. 

