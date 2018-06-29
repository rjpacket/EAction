### 1.Header通用参数

|参数|类型|描述|
|:-|:-|:-|
|app_type|String|app的平台类型，比如ios/android/web|
|device_id|String|设备号|
|version|String|版本号|
|model|String|手机型号，比如HUAWEI-P20|
|brand|String|手机品牌|
|osversion|String|手机系统版本|
|access_user_token|String|登录用户的验证token|
|sign|String|请求的验签，由固定算法得出，保证每条请求sign的唯一性|

### 2.登录接口

|参数|类型|描述|
|:-|:-|:-|
|url|post|/api/v1/login|
|phone|String|手机号|
|code|String|验证码|
|password|String|密码|

### 3.获取验证码接口

|参数|类型|描述|
|:-|:-|:-|
|url|post|/api/v1/identify|
|phone|String|手机号|
