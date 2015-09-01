package com.rms.base.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.implments.DefaultColumnMeta;
import com.rms.base.jdbc.implments.DefaultSchemaMeta;
import com.rms.base.jdbc.implments.DefaultTableMeta;
import com.rms.base.jdbc.implments.JDBCFactory;
import com.rms.base.jdbc.model.CatalogMeta;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCQueryResult;

/**
 *
 * @author ri.meisei
 * @since 2015/08/19
 */
public abstract class AbstractJDBCDatabaseMetaData implements JDBCDataBaseMetaData {

	private final DatabaseMetaData databaseMetaData;

	private final String CATALOG_SEPARATOR;

	private Map<String, CatalogMeta> catalogMetaMap = new HashMap<>();

	private Map<String, SchemaMeta> schemaMetaMap = new HashMap<>();

	private Map<String, TableMeta> tableMetaMap = new HashMap<>();

	protected AbstractJDBCDatabaseMetaData(DatabaseMetaData databaseMetaData) throws SQLException {

		Assertion.assertNotNull("databaseMetaData", databaseMetaData);

		this.databaseMetaData = databaseMetaData;

		this.CATALOG_SEPARATOR = databaseMetaData.getCatalogSeparator();

		initialMetaData();
	}

	private void initialMetaData() throws SQLException {

		setCatalogMetas();
		for (CatalogMeta catalogMeta : catalogMetaMap.values()) {
			setSchemaMetas(catalogMeta);
		}
		for (SchemaMeta catalogMeta : schemaMetaMap.values()) {
			setTableMetas(catalogMeta);
		}
		for (TableMeta tableMeta : tableMetaMap.values()) {
			setColumnMetas(tableMeta);
			setPrimaryKeys(tableMeta);
		}
	}

	private void setCatalogMetas() throws SQLException {

		JDBCQueryResult catalogQueryResult = null;

		catalogQueryResult = JDBCFactory.newJDBCQueryResult(databaseMetaData.getCatalogs());
		while (catalogQueryResult.hasNext()) {
			CatalogMeta catalogMeta = JDBCFactory.newCatalogMeta();
			// 1.TABLE_CAT String =>カタログ名
			catalogMeta.setCatalogName(catalogQueryResult.getValue("TABLE_CAT"));

			catalogMetaMap.put(catalogMeta.getCatalogName(), catalogMeta);
		}
	}

	private void setSchemaMetas(CatalogMeta catalogMeta) throws SQLException {

		String catalogName = catalogMeta.getCatalogName();

		JDBCQueryResult schemaQueryResult = null;
		schemaQueryResult = JDBCFactory.newJDBCQueryResult(databaseMetaData.getSchemas(catalogName, null));
		while (schemaQueryResult.hasNext()) {
			DefaultSchemaMeta schemaMeta = (DefaultSchemaMeta) JDBCFactory.newSchemaMeta();
			// 1.TABLE_SCHEM String =>スキーマ名
			schemaMeta.setCatalogName(catalogName);
			// 2.TABLE_CATALOG String =>カタログ名(nullの可能性がある)
			schemaMeta.setSchemaName(schemaQueryResult.getValue("TABLE_SCHEM"));

			catalogMeta.getSchemaMetas().add(schemaMeta);
			String schemaName = catalogName + CATALOG_SEPARATOR + schemaMeta.getSchemaName();
			schemaMetaMap.put(schemaName, schemaMeta);
		}
	}

	private void setTableMetas(SchemaMeta schemaMeta) throws SQLException {

		String catalogName = schemaMeta.getCatalogName();
		String schemaName = schemaMeta.getSchemaName();

		JDBCQueryResult tableQueryResult = null;
		tableQueryResult = JDBCFactory.newJDBCQueryResult(databaseMetaData.getTables(catalogName, schemaName, null, null));

		while (tableQueryResult.hasNext()) {
			/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
			// String tableCatalog = tableQueryResult.getValue("TABLE_CAT");
			// if (tableCatalog == null && catalogName != null) {
			// tableCatalog = catalogName;
			// }

			/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
			// String tableSchema =
			// tableQueryResult.getValue("TABLE_SCHEM");
			// if (tableSchema == null && schemaName != null) {
			// tableSchema = schemaName;
			// }

			/** TABLE_NAME String => テーブル名 */
			String tableName = tableQueryResult.getValue("TABLE_NAME");

			/**
			 * TABLE_TYPE String => テーブルのタイプ。典型的なタイプは、「TABLE」、「VIEW」、「SYSTEM
			 * TABLE」、「GLOBAL TEMPORARY」、「LOCAL TEMPORARY」、「ALIAS」、「SYNONYM」である
			 */
			String tableType = null;
			if (tableQueryResult.hasColumn("TABLE_TYPE")) {
				tableType = tableQueryResult.getValue("TABLE_TYPE");
			}

			/** REMARKS String => テーブルに関する説明 */
			String remarks = null;
			if (tableQueryResult.hasColumn("REMARKS")) {
				remarks = tableQueryResult.getValue("REMARKS");
			}

			/** TYPE_CAT String => タイプのカタログ (null の可能性がある) */
			String tableTypeCatalog = null;
			if (tableQueryResult.hasColumn("TYPE_CAT")) {
				tableTypeCatalog = tableQueryResult.getValue("TYPE_CAT");
			}

			/** TYPE_SCHEM String => タイプのスキーマ (null の可能性がある) */
			String tableTypeSchema = null;
			if (tableQueryResult.hasColumn("TYPE_SCHEM")) {
				tableTypeSchema = tableQueryResult.getValue("TYPE_SCHEM");
			}

			/** TYPE_NAME String => タイプ名 (null の可能性がある) */
			String typeName = null;
			if (tableQueryResult.hasColumn("TYPE_NAME")) {
				typeName = tableQueryResult.getValue("TYPE_NAME");
			}

			/**
			 * SELF_REFERENCING_COL_NAME String => タイプ指定されたテーブルの指定された「識別子」列の名前
			 * (null の可能性がある)
			 */
			String selfReferencingColumnName = null;
			if (tableQueryResult.hasColumn("SELF_REFERENCING_COL_NAME")) {
				selfReferencingColumnName = tableQueryResult.getValue("SELF_REFERENCING_COL_NAME");
			}

			/**
			 * REF_GENERATION String => SELF_REFERENCING_COL_NAME
			 * の値の作成方法を指定する。値は、「SYSTEM」、「USER」、「DERIVED」(null の可能性がある)
			 */
			String referenceGeneration = null;
			if (tableQueryResult.hasColumn("REF_GENERATION")) {
				referenceGeneration = tableQueryResult.getValue("REF_GENERATION");
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

	private void setColumnMetas(TableMeta tableMeta) throws SQLException {

		String catalogName = tableMeta.getCatalogName();
		String schemaName = tableMeta.getSchemaName();
		String tableName = tableMeta.getTableName();

		JDBCQueryResult columnQueryResult = null;
		columnQueryResult = JDBCFactory.newJDBCQueryResult(databaseMetaData.getColumns(catalogName, schemaName, tableName, null));

		while (columnQueryResult.hasNext()) {

			/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
			// String tableCatalog = jdbcQueryResult.getValue("TABLE_CAT");

			/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
			// String tableSchema = jdbcQueryResult.getValue("TABLE_SCHEM");

			/** TABLE_NAME String => テーブル名 */
			// String tableName = jdbcQueryResult.getValue("TABLE_NAME");

			/** COLUMN_NAME String => 列名 */
			String columnName = columnQueryResult.getValue("COLUMN_NAME");

			/** DATA_TYPE int => java.sql.Types からの SQL の型 */
			Integer dataType = columnQueryResult.getValue("DATA_TYPE");

			/** TYPE_NAME String => データソース依存の型名。UDT の場合、型名は完全指定 */
			String typeName = columnQueryResult.getValue("TYPE_NAME");

			/** COLUMN_SIZE int => 列サイズ */
			Integer columnSize = columnQueryResult.getValue("COLUMN_SIZE");

			/** BUFFER_LENGTH - 未使用。 */

			/**
			 * DECIMAL_DIGITS int => 小数点以下の桁数。DECIMAL_DIGITS
			 * が適用できないデータ型の場合は、Null が返される。
			 */
			Integer decimalDits = columnQueryResult.getValue("DECIMAL_DIGITS");

			/** NUM_PREC_RADIX int => 基数 (通常は、10 または 2 のどちらか) */
			Integer numPrecRadix = columnQueryResult.getValue("NUM_PREC_RADIX");

			/**
			 * NULLABLE int => NULL は許されるか。<br>
			 * columnNoNulls - NULL 値を許さない可能性がある<br>
			 * columnNullable - 必ず NULL 値を許す<br>
			 * columnNullableUnknown - NULL 値を許すかどうかは不明<br>
			 */
			Integer nullable = columnQueryResult.getValue("NULLABLE");

			/** REMARKS String => 列を記述するコメント (null の可能性がある) */
			String remarks = columnQueryResult.getValue("REMARKS");

			/**
			 * COLUMN_DEF String => 列のデフォルト値。単一引用符で囲まれた値は、文字列として解釈されるべき (null
			 * の可能性がある)
			 */
			String columnDefaultValue = columnQueryResult.getValue("COLUMN_DEF");

			/** SQL_DATA_TYPE int => 未使用 */

			/** SQL_DATETIME_SUB int => 未使用 */

			/** CHAR_OCTET_LENGTH int => char の型については列の最大バイト数 */
			String charOctetLength = columnQueryResult.getValue("CHAR_OCTET_LENGTH");

			/** ORDINAL_POSITION int => テーブル中の列のインデックス (1 から始まる) */
			Integer ordinalPosition = columnQueryResult.getValue("ORDINAL_POSITION");

			/**
			 * IS_NULLABLE String => 列で NULL 値を許可するかどうかの判断に ISO 規則が使用される。<br>
			 * YES --- 列が NULL を許可する場合<br>
			 * NO --- 列が NULL を許可しない場合<br>
			 * 空の文字列 --- 列が NULL 値を許可するかどうか不明である場合<br>
			 */
			String isNullable = columnQueryResult.getValue("IS_NULLABLE");

			/**
			 * SCOPE_CATALOG String => 参照属性のスコープであるテーブルのカタログ (DATA_TYPE が REF
			 * でない場合は null)
			 */
			String scopeCatalog = null;
			if (columnQueryResult.hasColumn("SCOPE_CATALOG")) {
				scopeCatalog = columnQueryResult.getValue("SCOPE_CATALOG");
			}

			/**
			 * SCOPE_SCHEMA String => 参照属性のスコープであるテーブルのスキーマ (DATA_TYPE が REF
			 * でない場合は null)
			 */
			String scopeSchema = columnQueryResult.getValue("SCOPE_SCHEMA");

			/**
			 * SCOPE_TABLE String => 参照属性のスコープであるテーブル名 (DATA_TYPE が REF でない場合は
			 * null)
			 */
			String scopeTable = columnQueryResult.getValue("SCOPE_TABLE");

			/**
			 * SOURCE_DATA_TYPE short => 個別の型またはユーザー生成 Ref
			 * 型のソースの型、java.sql.Types の SQL 型 (DATA_TYPE が DISTINCT またはユーザー生成
			 * REF でない場合は null)
			 */
			Integer sourceDataType = columnQueryResult.getValue("SOURCE_DATA_TYPE");

			/**
			 * IS_AUTOINCREMENT String => この列が自動インクリメントされるかどうかを示す<br>
			 * YES --- 列が自動インクリメントされる場合<br>
			 * NO --- 列が自動インクリメントされない場合<br>
			 * 空の文字列 --- 列が自動インクリメントされるかどうかが判断できない場合<br>
			 */
			String isAutoIncrement = columnQueryResult.getValue("IS_AUTOINCREMENT");

			/**
			 * IS_GENERATEDCOLUMN String => これが生成された列かどうかを示す<br>
			 * YES --- これが生成された列である場合<br>
			 * NO --- これが生成された列でない場合<br>
			 * 空の文字列 --- これが生成された列かどうかが判断できない場合<br>
			 */
			String isGeneratedColumn = null;
			if (columnQueryResult.hasColumn("IS_GENERATEDCOLUMN")) {
				scopeCatalog = columnQueryResult.getValue("IS_GENERATEDCOLUMN");
			}
			DefaultColumnMeta columnMeta = (DefaultColumnMeta) JDBCFactory.newColumnMeta();
			columnMeta.setCatalogName(catalogName);
			columnMeta.setSchemaName(schemaName);
			columnMeta.setTableName(tableName);
			columnMeta.setColumnName(columnName);
			columnMeta.setDataType(dataType);
			columnMeta.setTypeName(typeName);
			columnMeta.setColumnSize(columnSize);
			columnMeta.setDecimalDits(decimalDits);
			columnMeta.setNumPrecRadix(numPrecRadix);
			columnMeta.setNullable(nullable);
			columnMeta.setRemarks(remarks);
			columnMeta.setColumnDefaultValue(columnDefaultValue);
			columnMeta.setCharOctetLength(charOctetLength);
			columnMeta.setOrdinalPosition(ordinalPosition);
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

	private void setPrimaryKeys(TableMeta tableMeta) throws SQLException {

		String catalogName = tableMeta.getCatalogName();
		String schemaName = tableMeta.getSchemaName();
		String tableName = tableMeta.getTableName();

		JDBCQueryResult primaryKeysQueryResult = null;
		primaryKeysQueryResult = JDBCFactory.newJDBCQueryResult(databaseMetaData.getPrimaryKeys(catalogName, schemaName, tableName));
		while (primaryKeysQueryResult.hasNext()) {
			/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */

			/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */

			/** TABLE_NAME String => テーブル名 */

			/** 4.COLUMN_NAME String =>列名 */
			String columnName = primaryKeysQueryResult.getValue("COLUMN_NAME");

			/** 5.KEY_SEQ short =>主キー内の連番(値1は主キーの最初の列、値2は主キーの2番目の列を表す)。 */
			Integer keySequence = primaryKeysQueryResult.getValue("KEY_SEQ");

			/** 6.PK_NAME String =>主キー名(nullの可能性がある) */
			String primaryKeyName = primaryKeysQueryResult.getValue("PK_NAME");

			tableMeta.setPrimaryKeyName(primaryKeyName);

			DefaultColumnMeta defaultColumnMeta = (DefaultColumnMeta) tableMeta.getColumnMeta(columnName);
			defaultColumnMeta.setPrimaryKey(true);
			defaultColumnMeta.setKeySequence(keySequence);
		}
	}

	@Override
	public String getDatabaseProductName() throws SQLException {

		return databaseMetaData.getDatabaseProductName();
	}

	@Override
	public String getDatabaseProductVersion() throws SQLException {

		return databaseMetaData.getDatabaseProductVersion();
	}

	@Override
	public int getDatabaseMajorVersion() throws SQLException {

		return databaseMetaData.getDatabaseMajorVersion();
	}

	@Override
	public int getDatabaseMinorVersion() throws SQLException {

		return databaseMetaData.getDatabaseMinorVersion();
	}

	@Override
	public String getDriverName() throws SQLException {

		return databaseMetaData.getDriverName();
	}

	@Override
	public String getDriverVersion() throws SQLException {

		return databaseMetaData.getDriverVersion();
	}

	@Override
	public int getDriverMajorVersion() {

		return databaseMetaData.getDriverMajorVersion();
	}

	@Override
	public int getDriverMinorVersion() {

		return databaseMetaData.getDriverMinorVersion();
	}

	@Override
	public int getJDBCMajorVersion() throws SQLException {

		return databaseMetaData.getJDBCMajorVersion();
	}

	@Override
	public int getJDBCMinorVersion() throws SQLException {

		return databaseMetaData.getJDBCMinorVersion();
	}

	@Override
	public String getURL() throws SQLException {

		return databaseMetaData.getURL();
	}

	@Override
	public List<CatalogMeta> getCatalogMetas() throws SQLException {

		return new ArrayList<CatalogMeta>(catalogMetaMap.values());
	}

	@Override
	public CatalogMeta getCatalogMeta() throws SQLException {

		String catalogName = databaseMetaData.getConnection().getCatalog();
		return getCatalogMeta(catalogName);
	}

	@Override
	public CatalogMeta getCatalogMeta(String catalogName) throws SQLException {

		return catalogMetaMap.get(catalogName);
	}

	@Override
	public List<SchemaMeta> getSchemaMetas() throws SQLException {

		return getCatalogMeta().getSchemaMetas();
	}

	@Override
	public List<SchemaMeta> getSchemaMetas(String catalogName) throws SQLException {

		return getCatalogMeta(catalogName).getSchemaMetas();
	}

	@Override
	public SchemaMeta getSchemaMeta(String schemaName) throws SQLException {

		return getSchemaMeta(getCatalogMeta().getCatalogName(), schemaName);
	}

	@Override
	public SchemaMeta getSchemaMeta(String catalogName, String schemaName) throws SQLException {

		List<SchemaMeta> schemaMetas = getSchemaMetas(catalogName);
		for (SchemaMeta schemaMeta : schemaMetas) {
			if (schemaMeta.getSchemaName().equals(schemaName)) {
				return schemaMeta;
			}
		}

		return null;
	}

	@Override
	public List<TableMeta> getTableMetas(String schemaName) throws SQLException {

		CatalogMeta catalogMeta = getCatalogMeta();
		SchemaMeta schemaMeta = getSchemaMeta(catalogMeta.getCatalogName(), schemaName);
		if (schemaMeta != null) {
			return schemaMeta.getTableMetas();
		}

		return null;
	}

	@Override
	public List<TableMeta> getTableMetas(String catalogName, String schemaName) throws SQLException {

		return getSchemaMeta(catalogName, schemaName).getTableMetas();
	}

	@Override
	public TableMeta getTableMeta(String schemaName, String tableName) throws SQLException {

		List<TableMeta> tableMetas = getTableMetas(getCatalogMeta().getCatalogName(), schemaName);
		for (TableMeta tableMeta : tableMetas) {
			if (tableMeta.getTableName().equals(schemaName)) {
				return tableMeta;
			}
		}

		return null;
	}

	@Override
	public TableMeta getTableMeta(String catalogName, String schemaName, String tableName) throws SQLException {

		List<TableMeta> tableMetas = getTableMetas(catalogName, schemaName);
		for (TableMeta tableMeta : tableMetas) {
			if (tableMeta.getTableName().equals(schemaName)) {
				return tableMeta;
			}
		}

		return null;
	}
}
