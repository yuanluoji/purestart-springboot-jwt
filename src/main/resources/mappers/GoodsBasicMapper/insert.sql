INSERT INTO goods_basic(
    code,
    barcode,
    short_name,
    photos,
    properties,
    unit,
    state,
    created,
    modified,
    version
)VALUES (
    <@p name="r.code"/>,
    <@p name="r.barcode"/>,
    <@p name="r.shortName"/>,
    <@p name="r.photos"/>,
    <@p name="r.properties"/>,
    <@p name="r.unit"/>,
    <@p name="r.state"/>,
    <@p name="r.created"/>,
    <@p name="r.modified"/>,
    <@p name="r.version"/>
)