package com.rms.base.jdbc.implments;

import java.math.BigDecimal;
import java.sql.DatabaseMetaData;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.model.CatalogMeta;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.base.logging.Logger;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCQueryExecutor;

/**
 *
 * @author ri.meisei
 * @since 2015/08/19
 */
public abstract class AbstractJDBCDatabaseMetaData implements JDBCDataBaseMetaData {
	private static final Logger logger = Logger.getLogger(AbstractJDBCDatabaseMetaData.class);
	private Integer databaseMajorVersion;

	private Integer databaseMinorVersion;

	private String databaseProductName;

	private String databaseProductVersion;

	private Integer driverMajorVersion;

	private Integer driverMinorVersion;

	private String driverName;

	private String driverVersion;

	private Integer jdbcMajorVersion;

	private Integer jdbcMinorVersion;

	private String url;

	private String currentCatalogName;

	private final String CATALOG_SEPARATOR;

	private final Map<String, CatalogMeta> catalogMetaMap = new HashMap<>();

	private final Map<String, SchemaMeta> schemaMetaMap = new HashMap<>();

	private final Map<String, TableMeta> tableMetaMap = new HashMap<>();

	protected AbstractJDBCDatabaseMetaData(DatabaseMetaData databaseMetaData) throws SQLException {

		Assertion.assertNotNull("databaseMetaData", databaseMetaData);

		databaseMajorVersion = databaseMetaData.getDatabaseMajorVersion();

		databaseMinorVersion = databaseMetaData.getDatabaseMajorVersion();

		databaseProductName = databaseMetaData.getDatabaseProductName();

		databaseProductVersion = databaseMetaData.getDatabaseProductVersion();

		driverMajorVersion = databaseMetaData.getDriverMajorVersion();

		driverMinorVersion = databaseMetaData.getDriverMinorVersion();

		driverName = databaseMetaData.getDriverName();

		driverVersion = databaseMetaData.getDriverVersion();

		jdbcMajorVersion = databaseMetaData.getJDBCMajorVersion();

		jdbcMinorVersion = databaseMetaData.getJDBCMinorVersion();

		url = databaseMetaData.getURL();

		currentCatalogName = databaseMetaData.getConnection().getCatalog();

		this.CATALOG_SEPARATOR = databaseMetaData.getCatalogSeparator();

		initialMetaData(databaseMetaData);
	}

	private void initialMetaData(DatabaseMetaData databaseMetaData) throws SQLException {

		setCatalogMetas(databaseMetaData);

		if (databaseProductName.equals("Oracle")) {
			setSchemaMetas(databaseMetaData, null);
		}
		for (CatalogMeta catalogMeta : catalogMetaMap.values()) {
			setSchemaMetas(databaseMetaData, catalogMeta);
		}
		for (SchemaMeta catalogMeta : schemaMetaMap.values()) {
			setTableMetas(databaseMetaData, catalogMeta);
		}
		for (TableMeta tableMeta : tableMetaMap.values()) {
			setColumnMetas(databaseMetaData, tableMeta);
			setPrimaryKeys(databaseMetaData, tableMeta);
		}
	}

	private void setCatalogMetas(DatabaseMetaData databaseMetaData) throws SQLException {

		JDBCQueryExecutor catalogQueryExecutor = JDBCFactory.newJDBCQueryExecutor(databaseMetaData.getCatalogs());

		while (catalogQueryExecutor.hasNext()) {
			CatalogMeta catalogMeta = JDBCFactory.newCatalogMeta();
			// 1.TABLE_CAT String =>カタログ名
			catalogMeta.setCatalogName(catalogQueryExecutor.getValue("TABLE_CAT"));

			catalogMetaMap.put(catalogMeta.getCatalogName(), catalogMeta);
		}
	}

	private void setSchemaMetas(DatabaseMetaData databaseMetaData, CatalogMeta catalogMeta) throws SQLException {

		String catalogName = null;
		if (catalogMeta != null) {
			catalogName = catalogMeta.getCatalogName();
		}
		JDBCQueryExecutor schemaQueryExecutor = null;
		schemaQueryExecutor = JDBCFactory.newJDBCQueryExecutor(databaseMetaData.getSchemas(catalogName, null));
		while (schemaQueryExecutor.hasNext()) {
			DefaultSchemaMeta schemaMeta = (DefaultSchemaMeta) JDBCFactory.newSchemaMeta();
			// 1.TABLE_SCHEM String =>スキーマ名
			schemaMeta.setCatalogName(catalogName);
			// 2.TABLE_CATALOG String =>カタログ名(nullの可能性がある)
			schemaMeta.setSchemaName(schemaQueryExecutor.getValue("TABLE_SCHEM"));
			if (catalogMeta != null) {
				catalogMeta.addSchemaMeta(schemaMeta);
			}
			String schemaName = schemaMeta.getSchemaName();
			schemaMetaMap.put(schemaName, schemaMeta);
		}
	}

	private void setTableMetas(DatabaseMetaData databaseMetaData, SchemaMeta schemaMeta) throws SQLException {

		String catalogName = schemaMeta.getCatalogName();
		String schemaName = schemaMeta.getSchemaName();

		JDBCQueryExecutor tableQueryExecutor = null;
		tableQueryExecutor = JDBCFactory.newJDBCQueryExecutor(databaseMetaData.getTables(catalogName, schemaName, null, null));

		while (tableQueryExecutor.hasNext()) {
			/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
			// String tableCatalog = tableQueryExecutor.getValue("TABLE_CAT");
			// if (tableCatalog == null && catalogName != null) {
			// tableCatalog = catalogName;
			// }

			/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
			// String tableSchema =
			// tableQueryExecutor.getValue("TABLE_SCHEM");
			// if (tableSchema == null && schemaName != null) {
			// tableSchema = schemaName;
			// }

			/** TABLE_NAME String => テーブル名 */
			String tableName = tableQueryExecutor.getValue("TABLE_NAME");

			/**
			 * TABLE_TYPE String => テーブルのタイプ。典型的なタイプは、「TABLE」、「VIEW」、「SYSTEM
			 * TABLE」、「GLOBAL TEMPORARY」、「LOCAL TEMPORARY」、「ALIAS」、「SYNONYM」である
			 */
			String tableType = null;
			if (tableQueryExecutor.hasColumn("TABLE_TYPE")) {
				tableType = tableQueryExecutor.getValue("TABLE_TYPE");
			}

			/** REMARKS String => テーブルに関する説明 */
			String remarks = null;
			if (tableQueryExecutor.hasColumn("REMARKS")) {
				remarks = tableQueryExecutor.getValue("REMARKS");
			}

			/** TYPE_CAT String => タイプのカタログ (null の可能性がある) */
			String tableTypeCatalog = null;
			if (tableQueryExecutor.hasColumn("TYPE_CAT")) {
				tableTypeCatalog = tableQueryExecutor.getValue("TYPE_CAT");
			}

			/** TYPE_SCHEM String => タイプのスキーマ (null の可能性がある) */
			String tableTypeSchema = null;
			if (tableQueryExecutor.hasColumn("TYPE_SCHEM")) {
				tableTypeSchema = tableQueryExecutor.getValue("TYPE_SCHEM");
			}

			/** TYPE_NAME String => タイプ名 (null の可能性がある) */
			String typeName = null;
			if (tableQueryExecutor.hasColumn("TYPE_NAME")) {
				typeName = tableQueryExecutor.getValue("TYPE_NAME");
			}

			/**
			 * SELF_REFERENCING_COL_NAME String => タイプ指定されたテーブルの指定された「識別子」列の名前
			 * (null の可能性がある)
			 */
			String selfReferencingColumnName = null;
			if (tableQueryExecutor.hasColumn("SELF_REFERENCING_COL_NAME")) {
				selfReferencingColumnName = tableQueryExecutor.getValue("SELF_REFERENCING_COL_NAME");
			}

			/**
			 * REF_GENERATION String => SELF_REFERENCING_COL_NAME
			 * の値の作成方法を指定する。値は、「SYSTEM」、「USER」、「DERIVED」(null の可能性がある)
			 */
			String referenceGeneration = null;
			if (tableQueryExecutor.hasColumn("REF_GENERATION")) {
				referenceGeneration = tableQueryExecutor.getValue("REF_GENERATION");
			}

			DefaultTableMeta tableMeta = (DefaultTableMeta) JDBCFactory.newTableMeta();
			tableMeta.setCatalogName(catalogName);
			tableMeta.setSchemaName(schemaName);
			tableMeta.setTableName(tableName);
			tableMeta.setTableType(tableType);
			tableMeta.setRemarks(remarks);
			tableMeta.setTableTypeCatalog(tableTypeCatalog);
			tableMeta.setTableTypeSchema(tableTypeSchema);
			tableMeta.setTypeName(typeName);
			tableMeta.setSelfReferencingColumnName(selfReferencingColumnName);
			tableMeta.setReferenceGeneration(referenceGeneration);

			schemaMeta.addTableMeta(tableMeta);
			String tableNameKey = catalogName + CATALOG_SEPARATOR + schemaName + CATALOG_SEPARATOR + tableName;
			tableMetaMap.put(tableNameKey, tableMeta);
		}
	}

	private static void setColumnMetas(DatabaseMetaData databaseMetaData, TableMeta tableMeta) throws SQLException {

		String catalogName = tableMeta.getCatalogName();
		String schemaName = tableMeta.getSchemaName();
		String tableName = tableMeta.getTableName();

		JDBCQueryExecutor columnQueryExecutor = null;
		//SYS_NTzZ1IW+s8SbGauVQME+4/5A==
		try {
			columnQueryExecutor = JDBCFactory.newJDBCQueryExecutor(databaseMetaData.getColumns(catalogName, schemaName, tableName, null));
		} catch (Exception e) {
			// TODO: handle exception
			return;
		}

		while (columnQueryExecutor.hasNext()) {

			/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
			// String tableCatalog = jdbcQueryExecutor.getValue("TABLE_CAT");

			/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
			// String tableSchema = jdbcQueryExecutor.getValue("TABLE_SCHEM");

			/** TABLE_NAME String => テーブル名 */
			// String tableName = jdbcQueryExecutor.getValue("TABLE_NAME");

			/** COLUMN_NAME String => 列名 */
			String columnName = columnQueryExecutor.getValue("COLUMN_NAME");

			/** DATA_TYPE int => java.sql.Types からの SQL の型 */
			Integer dataType = ((Number) columnQueryExecutor.getValue("DATA_TYPE")).intValue();

			/** TYPE_NAME String => データソース依存の型名。UDT の場合、型名は完全指定 */
			String typeName = columnQueryExecutor.getValue("TYPE_NAME");

			/** COLUMN_SIZE int => 列サイズ */
			BigDecimal columnSize = columnQueryExecutor.getValue("COLUMN_SIZE");

			/** BUFFER_LENGTH - 未使用。 */

			/**
			 * DECIMAL_DIGITS int => 小数点以下の桁数。DECIMAL_DIGITS
			 * が適用できないデータ型の場合は、Null が返される。
			 */
			BigDecimal decimalDits = columnQueryExecutor.getValue("DECIMAL_DIGITS");

			/** NUM_PREC_RADIX int => 基数 (通常は、10 または 2 のどちらか) */
			BigDecimal numPrecRadix = columnQueryExecutor.getValue("NUM_PREC_RADIX");

			/**
			 * NULLABLE int => NULL は許されるか。<br>
			 * columnNoNulls - NULL 値を許さない可能性がある<br>
			 * columnNullable - 必ず NULL 値を許す<br>
			 * columnNullableUnknown - NULL 値を許すかどうかは不明<br>
			 */
			BigDecimal nullable = columnQueryExecutor.getValue("NULLABLE");

			/** REMARKS String => 列を記述するコメント (null の可能性がある) */
			String remarks = columnQueryExecutor.getValue("REMARKS");

			/**
			 * COLUMN_DEF String => 列のデフォルト値。単一引用符で囲まれた値は、文字列として解釈されるべき (null
			 * の可能性がある)
			 */
			String columnDefaultValue = null;
			// String columnDefaultValue =
			// columnQueryExecutor.getValue("COLUMN_DEF");

			/** SQL_DATA_TYPE int => 未使用 */

			/** SQL_DATETIME_SUB int => 未使用 */

			/** CHAR_OCTET_LENGTH int => char の型については列の最大バイト数 */
			BigDecimal charOctetLength = columnQueryExecutor.getValue("CHAR_OCTET_LENGTH");

			/** ORDINAL_POSITION int => テーブル中の列のインデックス (1 から始まる) */
			BigDecimal ordinalPosition = columnQueryExecutor.getValue("ORDINAL_POSITION");

			/**
			 * IS_NULLABLE String => 列で NULL 値を許可するかどうかの判断に ISO 規則が使用される。<br>
			 * YES --- 列が NULL を許可する場合<br>
			 * NO --- 列が NULL を許可しない場合<br>
			 * 空の文字列 --- 列が NULL 値を許可するかどうか不明である場合<br>
			 */
			String isNullable = columnQueryExecutor.getValue("IS_NULLABLE");

			/**
			 * SCOPE_CATALOG String => 参照属性のスコープであるテーブルのカタログ (DATA_TYPE が REF
			 * でない場合は null)
			 */
			String scopeCatalog = null;
			if (columnQueryExecutor.hasColumn("SCOPE_CATALOG")) {
				scopeCatalog = columnQueryExecutor.getValue("SCOPE_CATALOG");
			}

			/**
			 * SCOPE_SCHEMA String => 参照属性のスコープであるテーブルのスキーマ (DATA_TYPE が REF
			 * でない場合は null)
			 */
			String scopeSchema = columnQueryExecutor.getValue("SCOPE_SCHEMA");

			/**
			 * SCOPE_TABLE String => 参照属性のスコープであるテーブル名 (DATA_TYPE が REF でない場合は
			 * null)
			 */
			String scopeTable = columnQueryExecutor.getValue("SCOPE_TABLE");

			/**
			 * SOURCE_DATA_TYPE short => 個別の型またはユーザー生成 Ref
			 * 型のソースの型、java.sql.Types の SQL 型 (DATA_TYPE が DISTINCT またはユーザー生成
			 * REF でない場合は null)
			 */
			Integer sourceDataType = null;
			// Integer sourceDataType = ((Number)
			// columnQueryExecutor.getValue("SOURCE_DATA_TYPE")).intValue();
			/**
			 * IS_AUTOINCREMENT String => この列が自動インクリメントされるかどうかを示す<br>
			 * YES --- 列が自動インクリメントされる場合<br>
			 * NO --- 列が自動インクリメントされない場合<br>
			 * 空の文字列 --- 列が自動インクリメントされるかどうかが判断できない場合<br>
			 */
			String isAutoIncrement = columnQueryExecutor.getValue("IS_AUTOINCREMENT");

			/**
			 * IS_GENERATEDCOLUMN String => これが生成された列かどうかを示す<br>
			 * YES --- これが生成された列である場合<br>
			 * NO --- これが生成された列でない場合<br>
			 * 空の文字列 --- これが生成された列かどうかが判断できない場合<br>
			 */
			String isGeneratedColumn = null;
			if (columnQueryExecutor.hasColumn("IS_GENERATEDCOLUMN")) {
				scopeCatalog = columnQueryExecutor.getValue("IS_GENERATEDCOLUMN");
			}
			DefaultColumnMeta columnMeta = (DefaultColumnMeta) JDBCFactory.newColumnMeta();
			columnMeta.setCatalogName(catalogName);
			columnMeta.setSchemaName(schemaName);
			columnMeta.setTableName(tableName);
			columnMeta.setColumnName(columnName);
			try {
				columnMeta.setJdbcType(JDBCType.valueOf(dataType));
			} catch (Exception e) {
				//logger.debug(e);
			}
			columnMeta.setTypeName(typeName);
			columnMeta.setColumnSize(columnSize);
			columnMeta.setDecimalDits(0);
			columnMeta.setNumPrecRadix(numPrecRadix.intValue());
			columnMeta.setNullable(nullable.intValue());
			columnMeta.setRemarks(remarks);
			columnMeta.setColumnDefaultValue(columnDefaultValue);
			columnMeta.setCharOctetLength(String.valueOf(charOctetLength.intValue()));
			columnMeta.setOrdinalPosition(ordinalPosition.intValue());
			columnMeta.setIsNullable(isNullable);
			columnMeta.setScopeCatalog(scopeCatalog);
			columnMeta.setScopeSchema(scopeSchema);
			columnMeta.setScopeTable(scopeTable);
			columnMeta.setSourceDataType(sourceDataType);
			columnMeta.setIsAutoIncrement(isAutoIncrement);
			columnMeta.setIsGeneratedColumn(isGeneratedColumn);

			tableMeta.addColumnMeta(columnMeta);
		}
	}

	private static void setPrimaryKeys(DatabaseMetaData databaseMetaData, TableMeta tableMeta) throws SQLException {

		String catalogName = tableMeta.getCatalogName();
		String schemaName = tableMeta.getSchemaName();
		String tableName = tableMeta.getTableName();

		JDBCQueryExecutor primaryKeysQueryExecutor = null;
		primaryKeysQueryExecutor = JDBCFactory.newJDBCQueryExecutor(databaseMetaData.getPrimaryKeys(catalogName, schemaName, tableName));
		while (primaryKeysQueryExecutor.hasNext()) {
			/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */

			/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */

			/** TABLE_NAME String => テーブル名 */

			/** 4.COLUMN_NAME String =>列名 */
			String columnName = primaryKeysQueryExecutor.getValue("COLUMN_NAME");

			/** 5.KEY_SEQ short =>主キー内の連番(値1は主キーの最初の列、値2は主キーの2番目の列を表す)。 */
			BigDecimal keySequence = primaryKeysQueryExecutor.getValue("KEY_SEQ");

			/** 6.PK_NAME String =>主キー名(nullの可能性がある) */
			String primaryKeyName = primaryKeysQueryExecutor.getValue("PK_NAME");

			tableMeta.setPrimaryKeyName(primaryKeyName);

			DefaultColumnMeta defaultColumnMeta = (DefaultColumnMeta) tableMeta.getColumnMeta(columnName);
			if (defaultColumnMeta == null) {
				continue;
			}
			defaultColumnMeta.setPrimaryKey(true);
			defaultColumnMeta.setKeySequence(keySequence.intValue());
		}
	}

	@Override
	public String getDatabaseProductName() {

		return databaseProductName;
	}

	@Override
	public String getDatabaseProductVersion() {

		return databaseProductVersion;
	}

	@Override
	public int getDatabaseMajorVersion() {

		return databaseMajorVersion;
	}

	@Override
	public int getDatabaseMinorVersion() {

		return databaseMinorVersion;
	}

	@Override
	public String getDriverName() {

		return driverName;
	}

	@Override
	public String getDriverVersion() {

		return driverVersion;
	}

	@Override
	public int getDriverMajorVersion() {

		return driverMajorVersion;
	}

	@Override
	public int getDriverMinorVersion() {

		return driverMinorVersion;
	}

	@Override
	public int getJDBCMajorVersion() {

		return jdbcMajorVersion;
	}

	@Override
	public int getJDBCMinorVersion() {

		return jdbcMinorVersion;
	}

	@Override
	public String getURL() {

		return url;
	}

	@Override
	public boolean hasCatalog(String catalogName) {

		Assertion.assertNotBlank("catalogName", catalogName);

		return catalogMetaMap.containsKey(catalogName);
	}

	@Override
	public boolean hasSchema(String schemaName) {

		Assertion.assertNotBlank("schemaName", schemaName);

		return getCatalogMeta().contains(schemaName);
	}

	@Override
	public boolean hasSchema(String catalogName, String schemaName) {

		return hasCatalog(catalogName) && getCatalogMeta(catalogName).contains(schemaName);
	}

	@Override
	public boolean hasTable(String schemaName, String tableName) {

		Assertion.assertNotBlank("tableName", tableName);

		return hasSchema(schemaName) && getSchemaMeta(schemaName).contains(tableName);
	}

	@Override
	public boolean hasTable(String catalogName, String schemaName, String tableName) {

		return hasSchema(catalogName, schemaName) && getSchemaMeta(catalogName, schemaName).contains(tableName);
	}

	@Override
	public List<CatalogMeta> getCatalogMetas() {

		return new ArrayList<CatalogMeta>(catalogMetaMap.values());
	}

	@Override
	public CatalogMeta getCatalogMeta() {

		return getCatalogMeta(currentCatalogName);
	}

	@Override
	public CatalogMeta getCatalogMeta(String catalogName) {

		return catalogMetaMap.get(catalogName);
	}

	@Override
	public List<SchemaMeta> getSchemaMetas() {

		return getCatalogMeta().getSchemaMetas();
	}

	@Override
	public List<SchemaMeta> getSchemaMetas(String catalogName) {

		return getCatalogMeta(catalogName).getSchemaMetas();
	}

	@Override
	public SchemaMeta getSchemaMeta(String schemaName) {

		return getSchemaMeta(getCatalogMeta().getCatalogName(), schemaName);
	}

	@Override
	public SchemaMeta getSchemaMeta(String catalogName, String schemaName) {

		List<SchemaMeta> schemaMetas = getSchemaMetas(catalogName);
		for (SchemaMeta schemaMeta : schemaMetas) {
			if (schemaMeta.getSchemaName().equals(schemaName)) {
				return schemaMeta;
			}
		}

		return null;
	}

	@Override
	public List<TableMeta> getTableMetas(String schemaName) {

		CatalogMeta catalogMeta = getCatalogMeta();

		SchemaMeta schemaMeta = getSchemaMeta(catalogMeta.getCatalogName(), schemaName);
		if (schemaMeta != null) {
			return schemaMeta.getTableMetas();
		}

		return null;
	}

	@Override
	public List<TableMeta> getTableMetas(String catalogName, String schemaName) {
		if(catalogName==null){
		return schemaMetaMap.get(schemaName).getTableMetas();
		}
		return getSchemaMeta(catalogName, schemaName).getTableMetas();
	}

	@Override
	public TableMeta getTableMeta(String schemaName, String tableName) {

		List<TableMeta> tableMetas = getTableMetas(getCatalogMeta().getCatalogName(), schemaName);
		for (TableMeta tableMeta : tableMetas) {
			if (tableMeta.getTableName().equals(schemaName)) {
				return tableMeta;
			}
		}

		return null;
	}

	@Override
	public TableMeta getTableMeta(String catalogName, String schemaName, String tableName) {

		List<TableMeta> tableMetas = getTableMetas(catalogName, schemaName);
		for (TableMeta tableMeta : tableMetas) {
			if (tableMeta.getTableName().equals(schemaName)) {
				return tableMeta;
			}
		}

		return null;
	}
}
