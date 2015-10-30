package com.rms.base.jdbc.implments;

import java.sql.DatabaseMetaData;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rms.base.jdbc.JDBCUtil;
import com.rms.base.jdbc.JDBCValue;
import com.rms.base.jdbc.model.CatalogMeta;
import com.rms.base.jdbc.model.ColumnMeta;
import com.rms.base.jdbc.model.DataBaseMeta;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.base.logging.Logger;
import com.rms.base.util.TextUtil;
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

	private DataBaseMeta dataBaseMeta = new DataBaseMeta();

	private String currentCatalogName = null;

	private final List<CatalogMeta> catalogMetaCollection = new ArrayList<>();

	private final List<SchemaMeta> schemaMetaCollection = new ArrayList<>();

	private final List<TableMeta> tableMetaCollection = new ArrayList<>();

	private final List<ColumnMeta> columnMetaCollection = new ArrayList<>();

	protected AbstractJDBCDatabaseMetaData(DatabaseMetaData databaseMetaData) throws SQLException {

		Assertion.assertNotNull("databaseMetaData", databaseMetaData);

		inititalizeDatabaseMeta(databaseMetaData);

		initializeCatalogMetas(databaseMetaData);

		if (catalogMetaCollection.isEmpty()) {
			initializeSchemaMetas(databaseMetaData, null);
		} else {
			for (CatalogMeta catalogMeta : catalogMetaCollection) {
				initializeSchemaMetas(databaseMetaData, catalogMeta);
			}
		}

		for (SchemaMeta schemaMeta : schemaMetaCollection) {
			initializeTableMetas(databaseMetaData, schemaMeta);
		}

		for (TableMeta TableMeta : tableMetaCollection) {
			initializeColumnMetas(databaseMetaData, TableMeta);
		}
	}

	private void inititalizeDatabaseMeta(DatabaseMetaData databaseMetaData) throws SQLException {

		dataBaseMeta.setCatalogSeparator(databaseMetaData.getCatalogSeparator());
		dataBaseMeta.setDatabaseMajorVersion(databaseMetaData.getDatabaseMajorVersion());
		dataBaseMeta.setDatabaseMinorVersion(databaseMetaData.getDatabaseMajorVersion());
		dataBaseMeta.setDatabaseProductName(databaseMetaData.getDatabaseProductName());
		dataBaseMeta.setDatabaseProductVersion(databaseMetaData.getDatabaseProductVersion());
		dataBaseMeta.setDriverMajorVersion(databaseMetaData.getDriverMajorVersion());
		dataBaseMeta.setDriverMinorVersion(databaseMetaData.getDriverMinorVersion());
		dataBaseMeta.setDriverName(databaseMetaData.getDriverName());
		dataBaseMeta.setDriverVersion(databaseMetaData.getDriverVersion());
		dataBaseMeta.setJdbcMajorVersion(databaseMetaData.getJDBCMajorVersion());
		dataBaseMeta.setJdbcMinorVersion(databaseMetaData.getJDBCMinorVersion());
		dataBaseMeta.setUrl(databaseMetaData.getURL());
	}

	private void initializeCatalogMetas(DatabaseMetaData databaseMetaData) throws SQLException {

		currentCatalogName = databaseMetaData.getConnection().getCatalog();

		JDBCQueryExecutor catalogQueryExecutor = JDBCFactory.newJDBCQueryExecutor(databaseMetaData.getCatalogs());

		while (catalogQueryExecutor.hasNext()) {
			// 1.TABLE_CAT String =>カタログ名
			JDBCValue tableCatalog = catalogQueryExecutor.getJDBCValue("TABLE_CAT");

			CatalogMeta catalogMeta = new CatalogMeta();
			catalogMeta.setCatalogName(tableCatalog);

			catalogMetaCollection.add(catalogMeta);
		}
	}

	private void initializeSchemaMetas(DatabaseMetaData databaseMetaData, CatalogMeta catalogMeta) throws SQLException {

		String catalogName = (catalogMeta == null) ? null : catalogMeta.getCatalogName().getStringValue();

		ResultSet resultSetSchemaMeta = databaseMetaData.getSchemas(catalogName, null);

		JDBCQueryExecutor schemaQueryExecutor = JDBCFactory.newJDBCQueryExecutor(resultSetSchemaMeta);

		while (schemaQueryExecutor.hasNext()) {

			// 1.TABLE_SCHEM String =>スキーマ名
			JDBCValue tableSchema = schemaQueryExecutor.getJDBCValue("TABLE_SCHEM");

			// 2.TABLE_CATALOG String =>カタログ名(nullの可能性がある)
			JDBCValue tableCatalog = schemaQueryExecutor.getJDBCValue("TABLE_CATALOG");

			SchemaMeta schemaMeta = new SchemaMeta();
			schemaMeta.setSchemaName(tableSchema);
			schemaMeta.setCatalogName(tableCatalog);

			schemaMeta.setCatalogMeta(catalogMeta);

			schemaMetaCollection.add(schemaMeta);
		}
	}

	private void initializeTableMetas(DatabaseMetaData databaseMetaData, SchemaMeta schemaMeta) throws SQLException {

		String schemaName = (schemaMeta == null) ? null : schemaMeta.getSchemaName().getStringValue();
		CatalogMeta catalogMeta = (schemaMeta == null) ? null : schemaMeta.getCatalogMeta();
		String catalogName = (catalogMeta == null) ? null : catalogMeta.getCatalogName().getStringValue();

		ResultSet resultSetTableMeta = databaseMetaData.getTables(catalogName, schemaName, null, null);

		JDBCQueryExecutor tableQueryExecutor = JDBCFactory.newJDBCQueryExecutor(resultSetTableMeta);

		while (tableQueryExecutor.hasNext()) {
			/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
			JDBCValue tableCatalog = tableQueryExecutor.getJDBCValue("TABLE_CAT");

			/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
			JDBCValue tableSchema = tableQueryExecutor.getJDBCValue("TABLE_SCHEM");

			/** TABLE_NAME String => テーブル名 */
			JDBCValue tableName = tableQueryExecutor.getJDBCValue("TABLE_NAME");

			/**
			 * TABLE_TYPE String => テーブルのタイプ。典型的なタイプは、「TABLE」、「VIEW」、「SYSTEM TABLE」、「GLOBAL TEMPORARY」、 「LOCAL
			 * TEMPORARY」、「ALIAS」、「SYNONYM」である
			 */
			JDBCValue tableType = tableQueryExecutor.getJDBCValue("TABLE_TYPE");

			/** REMARKS String => テーブルに関する説明 */
			JDBCValue remarks = tableQueryExecutor.getJDBCValue("REMARKS");

			/** TYPE_CAT String => タイプのカタログ (null の可能性がある) */
			JDBCValue tableTypeCatalog = tableQueryExecutor.getJDBCValue("TYPE_CAT");

			/** TYPE_SCHEM String => タイプのスキーマ (null の可能性がある) */
			JDBCValue tableTypeSchema = tableQueryExecutor.getJDBCValue("TYPE_SCHEM");

			/** TYPE_NAME String => タイプ名 (null の可能性がある) */
			JDBCValue typeName = tableQueryExecutor.getJDBCValue("TYPE_NAME");

			/**
			 * SELF_REFERENCING_COL_NAME String => タイプ指定されたテーブルの指定された「識別子」列の名前 (null の可能性がある)
			 */
			JDBCValue selfReferencingColumnName = tableQueryExecutor.getJDBCValue("SELF_REFERENCING_COL_NAME");

			/**
			 * REF_GENERATION String => SELF_REFERENCING_COL_NAME の値の作成方法を指定する。値は、「SYSTEM」、「USER」、「DERIVED」(null
			 * の可能性がある)
			 */
			JDBCValue referenceGeneration = tableQueryExecutor.getJDBCValue("REF_GENERATION");

			TableMeta tableMeta = new TableMeta();
			tableMeta.setCatalogName(tableCatalog);
			tableMeta.setSchemaName(tableSchema);
			tableMeta.setTableName(tableName);
			tableMeta.setTableType(tableType);
			tableMeta.setRemarks(remarks);
			tableMeta.setTableTypeCatalog(tableTypeCatalog);
			tableMeta.setTableTypeSchema(tableTypeSchema);
			tableMeta.setTypeName(typeName);
			tableMeta.setSelfReferencingColumnName(selfReferencingColumnName);
			tableMeta.setReferenceGeneration(referenceGeneration);

			tableMeta.setSchemaMeta(schemaMeta);

			tableMetaCollection.add(tableMeta);
		}
	}

	private void initializeColumnMetas(DatabaseMetaData databaseMetaData, TableMeta tableMeta) throws SQLException {

		String tableName = (tableMeta == null) ? null : tableMeta.getTableName().getStringValue();
		SchemaMeta schemaMeta = (tableMeta == null) ? null : tableMeta.getSchemaMeta();
		String schemaName = (schemaMeta == null) ? null : schemaMeta.getSchemaName().getStringValue();
		CatalogMeta catalogMeta = (schemaMeta == null) ? null : schemaMeta.getCatalogMeta();
		String catalogName = (catalogMeta == null) ? null : catalogMeta.getCatalogName().getStringValue();

		ResultSet resultSetColumnMeta = databaseMetaData.getColumns(catalogName, schemaName, tableName, null);

		JDBCQueryExecutor columnQueryExecutor = JDBCFactory.newJDBCQueryExecutor(resultSetColumnMeta);

		while (columnQueryExecutor.hasNext()) {

			/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
			JDBCValue tableCatalog = columnQueryExecutor.getJDBCValue("TABLE_CAT");

			/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
			JDBCValue tableSchema = columnQueryExecutor.getJDBCValue("TABLE_SCHEM");

			/** TABLE_NAME String => テーブル名 */
			JDBCValue table = columnQueryExecutor.getJDBCValue("TABLE_NAME");

			/** COLUMN_NAME String => 列名 */
			JDBCValue columnName = columnQueryExecutor.getJDBCValue("COLUMN_NAME");

			/** DATA_TYPE int => java.sql.Types からの SQL の型 */
			JDBCValue dataType = columnQueryExecutor.getJDBCValue("DATA_TYPE");

			/** TYPE_NAME String => データソース依存の型名。UDT の場合、型名は完全指定 */
			JDBCValue typeName = columnQueryExecutor.getJDBCValue("TYPE_NAME");

			/** COLUMN_SIZE int => 列サイズ */
			JDBCValue columnSize = columnQueryExecutor.getJDBCValue("COLUMN_SIZE");

			/** BUFFER_LENGTH - 未使用。 */

			/**
			 * DECIMAL_DIGITS int => 小数点以下の桁数。DECIMAL_DIGITS が適用できないデータ型の場合は、Null が返される。
			 */
			JDBCValue decimalDits = columnQueryExecutor.getJDBCValue("DECIMAL_DIGITS");

			/** NUM_PREC_RADIX int => 基数 (通常は、10 または 2 のどちらか) */
			JDBCValue numPrecRadix = columnQueryExecutor.getJDBCValue("NUM_PREC_RADIX");

			/**
			 * NULLABLE int => NULL は許されるか。<br>
			 * columnNoNulls - NULL 値を許さない可能性がある<br>
			 * columnNullable - 必ず NULL 値を許す<br>
			 * columnNullableUnknown - NULL 値を許すかどうかは不明<br>
			 */
			JDBCValue nullable = columnQueryExecutor.getJDBCValue("NULLABLE");

			/** REMARKS String => 列を記述するコメント (null の可能性がある) */
			JDBCValue remarks = columnQueryExecutor.getJDBCValue("REMARKS");

			/**
			 * COLUMN_DEF String => 列のデフォルト値。単一引用符で囲まれた値は、文字列として解釈されるべき (null の可能性がある)
			 */
			JDBCValue columnDefaultValue = columnQueryExecutor.getJDBCValue("COLUMN_DEF");

			/** SQL_DATA_TYPE int => 未使用 */

			/** SQL_DATETIME_SUB int => 未使用 */

			/** CHAR_OCTET_LENGTH int => char の型については列の最大バイト数 */
			JDBCValue charOctetLength = columnQueryExecutor.getJDBCValue("CHAR_OCTET_LENGTH");

			/** ORDINAL_POSITION int => テーブル中の列のインデックス (1 から始まる) */
			JDBCValue ordinalPosition = columnQueryExecutor.getJDBCValue("ORDINAL_POSITION");

			/**
			 * IS_NULLABLE String => 列で NULL 値を許可するかどうかの判断に ISO 規則が使用される。<br>
			 * YES --- 列が NULL を許可する場合<br>
			 * NO --- 列が NULL を許可しない場合<br>
			 * 空の文字列 --- 列が NULL 値を許可するかどうか不明である場合<br>
			 */
			JDBCValue isNullable = columnQueryExecutor.getJDBCValue("IS_NULLABLE");

			/**
			 * SCOPE_CATALOG String => 参照属性のスコープであるテーブルのカタログ (DATA_TYPE が REF でない場合は null)
			 */
			JDBCValue scopeCatalog = columnQueryExecutor.getJDBCValue("SCOPE_CATALOG");

			/**
			 * SCOPE_SCHEMA String => 参照属性のスコープであるテーブルのスキーマ (DATA_TYPE が REF でない場合は null)
			 */
			JDBCValue scopeSchema = columnQueryExecutor.getJDBCValue("SCOPE_SCHEMA");

			/**
			 * SCOPE_TABLE String => 参照属性のスコープであるテーブル名 (DATA_TYPE が REF でない場合は null)
			 */
			JDBCValue scopeTable = columnQueryExecutor.getJDBCValue("SCOPE_TABLE");

			/**
			 * SOURCE_DATA_TYPE short => 個別の型またはユーザー生成 Ref 型のソースの型、java.sql.Types の SQL 型 (DATA_TYPE が DISTINCT
			 * またはユーザー生成 REF でない場合は null)
			 */
			JDBCValue sourceDataType = columnQueryExecutor.getJDBCValue("SOURCE_DATA_TYPE");

			/**
			 * IS_AUTOINCREMENT String => この列が自動インクリメントされるかどうかを示す<br>
			 * YES --- 列が自動インクリメントされる場合<br>
			 * NO --- 列が自動インクリメントされない場合<br>
			 * 空の文字列 --- 列が自動インクリメントされるかどうかが判断できない場合<br>
			 */
			JDBCValue isAutoIncrement = columnQueryExecutor.getJDBCValue("IS_AUTOINCREMENT");

			/**
			 * IS_GENERATEDCOLUMN String => これが生成された列かどうかを示す<br>
			 * YES --- これが生成された列である場合<br>
			 * NO --- これが生成された列でない場合<br>
			 * 空の文字列 --- これが生成された列かどうかが判断できない場合<br>
			 */
			JDBCValue isGeneratedColumn = null;
			if (columnQueryExecutor.hasColumn("IS_GENERATEDCOLUMN")) {
				scopeCatalog = columnQueryExecutor.getJDBCValue("IS_GENERATEDCOLUMN");
			}

			ColumnMeta columnMeta = new ColumnMeta();
			columnMeta.setCatalogName(tableCatalog);
			columnMeta.setSchemaName(tableSchema);
			columnMeta.setTableName(table);
			columnMeta.setColumnName(columnName);
			columnMeta.setJdbcType(JDBCType.valueOf(dataType.getStringValue()));
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

			columnMeta.setTableMeta(tableMeta);

			columnMetaCollection.add(columnMeta);
		}
	}

	private void setPrimaryKeys(DatabaseMetaData databaseMetaData, TableMeta tableMeta) throws SQLException {

		JDBCValue catalogName = tableMeta.getCatalogName();
		JDBCValue schemaName = tableMeta.getSchemaName();
		JDBCValue tableName = tableMeta.getTableName();

		JDBCQueryExecutor primaryKeysQueryExecutor = null;
		primaryKeysQueryExecutor = JDBCFactory.newJDBCQueryExecutor(databaseMetaData.getPrimaryKeys(catalogName.getStringValue(), schemaName.getStringValue(), tableName.getStringValue()));
		while (primaryKeysQueryExecutor.hasNext()) {
			/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */

			/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */

			/** TABLE_NAME String => テーブル名 */

			/** 4.COLUMN_NAME String =>列名 */
			JDBCValue columnName = primaryKeysQueryExecutor.getJDBCValue("COLUMN_NAME");

			/** 5.KEY_SEQ short =>主キー内の連番(値1は主キーの最初の列、値2は主キーの2番目の列を表す)。 */
			JDBCValue keySequence = primaryKeysQueryExecutor.getJDBCValue("KEY_SEQ");

			/** 6.PK_NAME String =>主キー名(nullの可能性がある) */
			JDBCValue primaryKeyName = primaryKeysQueryExecutor.getJDBCValue("PK_NAME");

			tableMeta.setPrimaryKeyName(primaryKeyName);

			ColumnMeta columnMeta = tableMeta.getColumnMeta(columnName);
			if (columnMeta == null) {
				continue;
			}
			columnMeta.setPrimaryKey(new JDBCValue(true));
			columnMeta.setKeySequence(keySequence);
		}
	}

	@Override
	public DataBaseMeta getDataBaseMeta() {

		return dataBaseMeta;
	}

	@Override
	public boolean hasCatalog(JDBCValue catalogName) {

		if (JDBCUtil.isNull(catalogName)) {
			return true;
		}

		return catalogMetaCollection.parallelStream().anyMatch(element -> element.getCatalogName().equals(catalogName));
	}

	@Override
	public boolean hasSchema(JDBCValue schemaName) {

		if (JDBCUtil.isNull(schemaName)) {
			return true;
		}

		return schemaMetaCollection.parallelStream().anyMatch(element -> element.getSchemaName().equals(schemaName));
	}

	@Override
	public boolean hasSchema(JDBCValue catalogName, JDBCValue schemaName) {

		if (JDBCUtil.isNull(schemaName)) {
			return true;
		}

		return schemaMetaCollection.parallelStream().anyMatch(schemaMeta -> schemaMeta.getSchemaName().equals(schemaName));
	}

	@Override
	public boolean hasTable(JDBCValue schemaName, JDBCValue tableName) {

		Assertion.assertNotNull("tableName", tableName);

		return hasSchema(schemaName) && getSchemaMeta(schemaName).contains(tableName);
	}

	@Override
	public boolean hasTable(JDBCValue catalogName, JDBCValue schemaName, JDBCValue tableName) {

		return hasSchema(catalogName, schemaName) && getSchemaMeta(catalogName, schemaName).contains(tableName);
	}

	@Override
	public CatalogMeta getCurrentCatalogMeta() {

		for (CatalogMeta catalogMeta : catalogMetaCollection) {
			if (TextUtil.isEquals(currentCatalogName, catalogMeta.getCatalogName().getStringValue())) {
				return catalogMeta;
			}
		}

		return null;
	}

	@Override
	public List<CatalogMeta> getCatalogMetas() {

		return catalogMetaCollection;
	}

	@Override
	public CatalogMeta getCatalogMeta(JDBCValue catalogName) {

		if (JDBCUtil.isNull(catalogName)) {
			return null;
		}

		for (CatalogMeta catalogMeta : catalogMetaCollection) {
			if (catalogMeta.getCatalogName().equals(catalogName)) {
				return catalogMeta;
			}
		}

		return null;
	}

	@Override
	public List<SchemaMeta> getSchemaMetas() {

		return getCurrentCatalogMeta().getSchemaMetas();
	}

	@Override
	public List<SchemaMeta> getSchemaMetas(JDBCValue catalogName) {

		return getCatalogMeta(catalogName).getSchemaMetas();
	}

	@Override
	public SchemaMeta getSchemaMeta(JDBCValue schemaName) {

		return getSchemaMeta(getCurrentCatalogMeta().getCatalogName(), schemaName);
	}

	@Override
	public SchemaMeta getSchemaMeta(JDBCValue catalogName, JDBCValue schemaName) {

		List<SchemaMeta> schemaMetas = getSchemaMetas(catalogName);
		for (SchemaMeta schemaMeta : schemaMetas) {
			if (schemaMeta.getSchemaName().equals(schemaName)) {
				return schemaMeta;
			}
		}

		return null;
	}

	@Override
	public List<TableMeta> getTableMetas(JDBCValue schemaName) {

		CatalogMeta catalogMeta = getCurrentCatalogMeta();

		SchemaMeta schemaMeta = getSchemaMeta(catalogMeta.getCatalogName(), schemaName);
		if (schemaMeta != null) {
			return schemaMeta.getTableMetas();
		}

		return null;
	}

	@Override
	public List<TableMeta> getTableMetas(JDBCValue catalogName, JDBCValue schemaName) {

		return tableMetaCollection.stream().filter(tableMeta -> {
			CatalogMeta catalogMeta = tableMeta.getSchemaMeta().getCatalogMeta();
			SchemaMeta schemaMeta = tableMeta.getSchemaMeta();

			return catalogMeta.getCatalogName().equals(catalogName) && schemaMeta.getCatalogName().equals(schemaMeta);
		}).collect(Collectors.toList());
	}

	@Override
	public TableMeta getTableMeta(JDBCValue schemaName, JDBCValue tableName) {

		List<TableMeta> tableMetas = getTableMetas(getCurrentCatalogMeta().getCatalogName(), schemaName);
		for (TableMeta tableMeta : tableMetas) {
			if (tableMeta.getTableName().equals(schemaName)) {
				return tableMeta;
			}
		}

		return null;
	}

	@Override
	public TableMeta getTableMeta(JDBCValue catalogName, JDBCValue schemaName, JDBCValue tableName) {

		List<TableMeta> tableMetas = getTableMetas(catalogName, schemaName);
		for (TableMeta tableMeta : tableMetas) {
			if (tableMeta.getTableName().equals(schemaName)) {
				return tableMeta;
			}
		}

		return null;
	}
}
