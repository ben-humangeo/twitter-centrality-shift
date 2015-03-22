namespace java com.thehumangeo.twitter.centrality.domain

struct StatusMetadata {
    1: required string isoLanguageCode;
    2: required string resultType;
}

struct StatusUserEntitiesDescription {

}

struct StatusUserEntities {

}

struct StatusUser {

}

struct StatusEntitiesUserMentions {

}

struct StatusEntitiesUrls {

}

struct StatusEntities {

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