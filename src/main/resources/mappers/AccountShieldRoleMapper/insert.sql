INSERT INTO account_shield_role(
    role_name,
    description,
    state,
    created,
    modified,
    version
)VALUES (
    <@p name="r.roleName"/>,
    <@p name="r.description"/>,
    <@p name="r.state"/>,
    <@p name="r.created"/>,
    <@p name="r.modified"/>,
    <@p name="r.version"/>
)