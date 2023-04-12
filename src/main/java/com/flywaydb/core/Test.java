package com.flywaydb.core;

import com.flywaydb.core.api.configuration.Configuration;
import com.flywaydb.core.internal.database.DatabaseType;
import com.flywaydb.core.internal.database.sqlite.SQLiteDatabaseType;
import com.flywaydb.core.internal.parser.ParsingContext;
import com.flywaydb.core.internal.sqlscript.SqlScript;
import com.flywaydb.core.internal.sqlscript.SqlScriptFactory;
import com.flywaydb.core.internal.sqlscript.SqlStatementIterator;
import com.flywaydb.core.internal.resource.NoopResourceProvider;
import com.flywaydb.core.internal.resource.StringResource;

public class Test {
    public static void main(String[] args) {
        String sql = "set odps.sql.submit.mode = script ;\n" +
                "\n" +
                "create table `rs_goods_sale_day_tmp1`\n" +
                "as\n" +
                "SELECT\n" +
                "   'a;' s, goodsSale.goods_sn,goodsSale.pipeline_code,goodsSale.sale_qty,gcr.category_id,dscp.first_category_id,dscp.second_category_id,dscp.third_category_id\n" +
                "    FROM\n" +
                "    (\n" +
                "    select goods.goods_sn,info.pipeline_code,sum(goods.qty) sale_qty\n" +
                "    from  (select * from ssvvods.cln_vevor_order_order_info_df where ds=max_pt('ssvvods.cln_vevor_order_order_info_df') ) info\n" +
                "left join (select * from ssvvods.cln_vevor_order_order_goods_df where ds=max_pt('ssvvods.cln_vevor_order_order_goods_df') ) goods\n" +
                " on info.order_sn=goods.order_sn\n" +
                " left join (select * from ssvvods.cln_vevor_order_order_info_extend_df where ds=max_pt('ssvvods.cln_vevor_order_order_info_extend_df') ) extend\n" +
                " on info.order_sn=extend.order_sn\n" +
                " where  info.pay_status in (3,4,5)\n" +
                " AND extend.is_test=0\n" +
                " AND info.completed_time> unix_timestamp(DATE_SUB(getdate(),60))\n" +
                " AND goods.goods_sn is not null\n" +
                " group by info.pipeline_code,goods.goods_sn\n" +
                "    ) goodsSale\n" +
                "left join\n" +
                " (select good_sn,category_id from ssvvods.s_vevor_goods_goods_category_relation_df where ds=max_pt('ssvvods.s_vevor_goods_goods_category_relation_df') and is_default=1 ) gcr\n" +
                "on goodsSale.goods_sn=gcr.good_sn\n" +
                "LEFT JOIN\n" +
                " (\n" +
                "      SELECT distinct l1.id first_category_id,l2.id second_category_id,l3.id  third_category_id\n" +
                "      FROM (\n" +
                "          SELECT gc.id, gc.parent_id, gc.sort FROM ssvvods.s_vevor_goods_goods_category_df gc WHERE gc.is_delete = 2 AND gc.ds=max_pt('ssvvods.s_vevor_goods_goods_category_df')\n" +
                "      ) l1\n" +
                "      LEFT JOIN (\n" +
                "          SELECT gc.id, gc.parent_id, gc.sort FROM ssvvods.s_vevor_goods_goods_category_df gc WHERE gc.is_delete = 2 AND gc.ds=max_pt('ssvvods.s_vevor_goods_goods_category_df')\n" +
                "      ) l2 ON l1.id = l2.parent_id\n" +
                "      LEFT JOIN (\n" +
                "          SELECT gc.id, gc.parent_id, gc.sort FROM ssvvods.s_vevor_goods_goods_category_df gc WHERE gc.is_delete = 2 AND gc.ds=max_pt('ssvvods.s_vevor_goods_goods_category_df')\n" +
                "          ) l3 ON l2.id = l3.parent_id\n" +
                "          WHERE l1.parent_id = 0\n" +
                "    )  dscp\n" +
                "    ON ( gcr.category_id = dscp.third_category_id)\n" +
                "    where goodsSale.goods_sn is not null ;\n" +
                "\n" +
                "    drop table if exists rs_goods_sale_day;\n" +
                "   alter table rs_goods_sale_day_tmp1 rename to rs_goods_sale_day;";

        final DatabaseType databaseType = new SQLiteDatabaseType();
        Configuration configuration = new Configuration();
        SqlScriptFactory sqlScriptFactory = databaseType.createSqlScriptFactory(configuration, new ParsingContext());
        SqlScript sqlScript = sqlScriptFactory.createSqlScript(new StringResource(sql),true,NoopResourceProvider.INSTANCE);
        SqlStatementIterator stm = sqlScript.getSqlStatements();
        stm.forEachRemaining((v) -> {
            System.out.println(v.getSql());
            System.out.println(v.canExecuteInTransaction());
        });

    }
}
