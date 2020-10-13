INSERT INTO account_basic(
    account_id,
    nick_name,
    password,
    sex,
    state,
    photo_url,
    created,
    modified,
    version
)VALUES (
    <@p name="r.accountId"/>,
    <@p name="r.nickName"/>,
    <@p name="r.password"/>,
    <@p name="r.sex"/>,
    <@p name="r.state"/>,
    <@p name="r.photoUrl"/>,
    <@p name="r.created"/>,
    <@p name="r.modified"/>,
    <@p name="r.version"/>
)