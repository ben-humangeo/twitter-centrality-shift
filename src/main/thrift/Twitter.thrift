namespace java com.thehumangeo.twitter.centrality.domain

struct StatusMetadata {
    1: required string isoLanguageCode;
    2: required string resultType;
}

struct StatusUserEntitiesDescription {
    1: required list<string> urls;
}

struct StatusUserEntities {
    1: required StatusUserEntitiesDescription description;
}

struct StatusUser {
    1: required i64 id;
    2: required string idString;
    3: required string name;
    4: required string screenName;
    5: required string location;
    6: optional string profileLocation;
    7: required string description;
    8: optional string url;
    9: required StatusUserEntities entities;
    10: required bool protect;
    11: required i32 followersCount;
    12: required i32 friendsCount;
    13: required i32 listedCount;
    14: required string createdAt;
    15: required i32 favouritesCount;
    16: required i32 utcOffset;
    17: required string timeZone;
    18: required bool geoEnabled;
    19: required bool verified;
    20: required i32 statusesCount;
    21: required string lang;
    22: required bool contributorsEnabled;
    23: required bool isTranslator;
    24: required bool isTranslationEnabled;
    25: required string profileBackgroundColor;
    26: required string profileBackgroundImageUrl;
    27: required string profileBackgroundImageUrlHttps;
    28: required bool profileBackgroundTitle;
    29: required string profileImageUrl;
    30: required string profileImageUrlHttps;
    31: required string profileLinkColor;
    32: required string profileSidebarBorderColor;
    33: required string profilesidebarFillColor;
    34: required string profileTextColor;
    35: required bool profileUseBackgroundImage;
    36: required bool defaultProfile;
    37: required bool defaultProfileImage;
    38: optional bool following;
    39: optional bool followRequestSent;
    40: optional string notifications;
}

struct StatusEntitiesUserMentions {
    1: required string screenName;
    2: required string name;
    3: required i32 id;
    4: required string idString;
    5: required list<i32> indices;
}

struct StatusEntitiesUrls {
    1: required string url;
    2: required string expandedUrl;
    3: required string displayUrl;
    4: required list<i32> indices;
}

struct StatusEntities {
    1: required list<string> hashtags;
    2: required list<string> symbols;
    3: required list<StatusEntitiesUserMentions> userMentions;
    4: required list<StatusEntitiesUrls> urls;
    5: required bool favorited;
    6: required bool retweeted;
    7: required bool possiblySensitive;
    8: required string lang;
}

struct Status {
    1: required StatusMetadata metadata;
    2: required string createdAt;
    3: required i64 id;
    4: required string idString;
    5: required string text;
    6: required string source;
    7: required bool truncated;
    8: required bool inReplyToStatusId;
    9: required bool inReplyToStatusIdStr;
    10: required bool inReplyToUserId;
    11: required bool inReplyToUserIdStr;
    12: required bool inReplyToScreenName;
}

struct SearchMetadata {
    1: required double completedIn;
    2: required i64 maxId;
    3: required string maxIdString;
    4: required string nextResults;
    5: required string query;
    6: required string refreshUrl;
    7: required i32 count;
    8: required i64 sinceId;
    9: required string sinceIdString;
}