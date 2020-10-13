INSERT INTO ref_account_shield_account_role(
    account_id,
    role_name,
    created,
    modified,
    version
)VALUES (
    <@p name="r.accountId"/>,
    <@p name="r.roleName"/>,
    <@p name="r.created"/>,
    <@p name="r.modified"/>,
    <@p name="r.version"/>
)