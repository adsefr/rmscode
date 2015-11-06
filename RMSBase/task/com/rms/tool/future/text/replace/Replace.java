package com.rms.tool.future.text.replace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Replace {

	public Replace() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Test
	public void replace() {

		Map<String, String> map = new HashMap<>();
		map.put("SKS.SYUKKAHIKIATEID", "TR_SYUKKAHIKIATE.SYUKKAHIKIATEID");
		map.put("SKS.SYUKKAYMD", "TR_SYUKKAHIKIATE.SYUKKAYMD");
		map.put("SKS.HIKIATEKBN", "TR_SYUKKAHIKIATE.HIKIATEKBN");
		map.put("SKS.SOKOCD", "TR_SYUKKAHIKIATE.SOKOCD");
		map.put("SKS.KIGYOID", "TR_SYUKKAHIKIATE.KIGYOID");
		map.put("SKS.NINUSICD", "TR_SYUKKAHIKIATE.NINUSICD");
		map.put("SKS.HIKIATETANIKBN", "TR_SYUKKAHIKIATE.HIKIATETANIKBN");
		map.put("SKS.SYUKKASAGYOYMD", "TR_SYUKKAHIKIATE.SYUKKASAGYOYMD");
		map.put("SKS.HIKIATESYORIJYOKYOKBN", "TR_SYUKKAHIKIATE.HIKIATESYORIJYOKYOKBN");
		map.put("SKS.HIKIATESYORIKEKKAKBN", "TR_SYUKKAHIKIATE.HIKIATESYORIKEKKAKBN");
		map.put("SKS.HIKIATEJISIMEISAIKENSU", "TR_SYUKKAHIKIATE.HIKIATEJISIMEISAIKENSU");
		map.put("SKS.HIKIATEKEPPINMEISAIKENSU", "TR_SYUKKAHIKIATE.HIKIATEKEPPINMEISAIKENSU");
		map.put("SKS.HIKIATEERRMEISAIKENSU", "TR_SYUKKAHIKIATE.HIKIATEERRMEISAIKENSU");
		map.put("SKS.HIKIATEJISISURYO", "TR_SYUKKAHIKIATE.HIKIATEJISISURYO");
		map.put("SKS.HIKIATEERRSURYO", "TR_SYUKKAHIKIATE.HIKIATEERRSURYO");
		map.put("SKS.HIKIATEJISIDATE", "TR_SYUKKAHIKIATE.HIKIATEJISIDATE");
		map.put("SKS.SYUKKATEISIMEISAIKENSU", "TR_SYUKKAHIKIATE.SYUKKATEISIMEISAIKENSU");
		map.put("SKS.SYUKKATEISISURYO", "TR_SYUKKAHIKIATE.SYUKKATEISISURYO");
		map.put("SKS.HINSITUKBN", "TR_SYUHIKIATDNMEIBTUSURYO.HINSITUKBN");
		map.put("SKS.SHOHINID", "TR_SYUHIKIATDNMEIBTUSURYO.SHOHINID");
		map.put("SKS.YOTEISURYO", "TR_SYUHIKIATDNMEIBTUSURYO.YOTEISURYO");
		map.put("SKS.HIKIATESURYO", "TR_SYUHIKIATDNMEIBTUSURYO.HIKIATESURYO");
		map.put("SKS.KEPPINSURYO", "TR_SYUHIKIATDNMEIBTUSURYO.HIKIATEKEPPINSURYO");
		map.put("SKS.HOJYUKANOSURYO", "TR_SYUHIKIATDNMEIBTUSURYO.HOJYUKANOSURYO");
		map.put("SKS.HIKIATEKANOSURYO", "TR_SYUHIKIATDNMEIBTUSURYO.HIKIATEKANOSURYO");
		map.put("SKS.HINSITUKBN", "TR_SYUHIKIATDNMEIBTUSURYO.HINSITUKBN");
		map.put("SKS.SYUKKATEISISURYO", "TR_SYUHIKIATDNMEIBTUSURYO.SYUKKATEISISURYO");
		map.put("SKS.SYUKKATEISIKBN", "TR_SYUHIKIATDNMEIBTUSURYO.SYUKKATEISIKBN");
		map.put("SKS.SYUKKATEISITAISYOFLG", "TR_SYUHIKIATDNMEIBTUSURYO.SYUKKATEISITAISYOFLG");
		map.put("SKS.SYUKKADENID", "TR_SYUHIKIATDNMEIBTUSURYO.SYUKKADENID");
		map.put("SKS.SYUKKADENIDEDA", "TR_SYUHIKIATDNMEIBTUSURYO.SYUKKADENIDEDA");
		map.put("SKS.SYUKKADENMEISAIID", "TR_SYUHIKIATDNMEIBTUSURYO.SYUKKADENMEISAIID");

		List<String> list = new ArrayList<>();
		list.add("INパラメータ/*syoriseqno*/");
		list.add("ROWNUM");
		list.add("INパラメータ/*syoriymd*/");
		list.add("SKS.KIGYOID");
		list.add("SKS.NINUSICD");
		list.add("MS_NINUSI.NINUSINM");
		list.add("SKS.SOKOCD");
		list.add("MS_SOKO.SOKONM");
		list.add("SKS.SYUKKAHIKIATEID");
		list.add("SKS.HIKIATEKBN");
		list.add("MVMS_KBNTI_WMS.KBNTINM1");
		list.add("SKS.HIKIATEKBN = '01'（01：仮引当）の場合 TR_SYUKKAGRP_YOTEI.SYUKKAYOTEIYMD それ以外の場合 SKS.SYUKKASAGYOYMD");
		list.add("SKS.HIKIATEKBN = '01'（01：仮引当）の場合 TR_SYUKKAGRP_YOTEI.SYUKKAGRPID それ以外の場合 TR_SYUKKAGRP_SYUKKASIJI.SYUKKAGRPID");
		list.add("SKS.HIKIATEKBN = '01'（01：仮引当）の場合 TR_SYUKKAGRP_YOTEI.SYUKKAYOTEIYMD それ以外の場合 TR_SYUKKAGRP_SYUKKASIJI.SYUKKAYOTEIYMD");
		list.add("TR_SYUKKAGRP_SYUKKASIJI.SYUKKASIJIYMD");
		list.add("SKS.HIKIATEKBN = '01'（01：仮引当）の場合 TR_SYUKKAGRP_YOTEI.SYUKKAKANRIHANYOKOMOKU1 それ以外の場合 TR_SYUKKAGRP_SYUKKASIJI.SYUKKAKANRIHANYOKOMOKU1");
		list.add("SKS.HIKIATEKBN = '01'（01：仮引当）の場合 TR_SYUKKAGRP_YOTEI.SYUKKAKANRIHANYOKOMOKU2 それ以外の場合 TR_SYUKKAGRP_SYUKKASIJI.SYUKKAKANRIHANYOKOMOKU2");
		list.add("SKS.HIKIATEKBN = '01'（01：仮引当）の場合 TR_SYUKKAGRP_YOTEI.SYUKKAKANRIHANYOKOMOKU3 それ以外の場合 TR_SYUKKAGRP_SYUKKASIJI.SYUKKAKANRIHANYOKOMOKU3");
		list.add("SKS.HIKIATEKBN = '01'（01：仮引当）の場合 TR_SYUKKAGRP_YOTEI.SYUKKAKANRIHANYOKOMOKU4 それ以外の場合 TR_SYUKKAGRP_SYUKKASIJI.SYUKKAKANRIHANYOKOMOKU4");
		list.add("SKS.HIKIATEKBN = '01'（01：仮引当）の場合 TR_SYUKKAGRP_YOTEI.SYUKKAKANRIHANYOKOMOKU5 それ以外の場合 TR_SYUKKAGRP_SYUKKASIJI.SYUKKAKANRIHANYOKOMOKU5");
		list.add("SKS.SYUKKAYMD");
		list.add("");
		list.add("SKS.HIKIATEKBN = '01'（01：仮引当）の場合 TR_SYUKKAGRP_YOTEI.SYUKKASIJIMOTOKBN それ以外の場合 TR_SYUKKAGRP_SYUKKASIJI.SYUKKASIJIMOTOKBN");
		list.add("SKS.HIKIATEKBN = '01'（01：仮引当）の場合 TR_SYUKKAGRP_YOTEI.DENSYUBE それ以外の場合 TR_SYUKKAGRP_SYUKKASIJI.DENSYUBE");
		list.add("SKS.HIKIATEKBN = '01'（01：仮引当）の場合 MVMS_KBNTI_WMS.KBNTINM1 それ以外の場合 MVMS_KBNTI_WMS.KBNTINM1");
		list.add("TR_SYUKKA_DEN.SYUKKADENID");
		list.add("TR_SYUKKA_DEN.SYUKKADENIDEDA");
		list.add("TR_SYUKKA_DEN.SYUKKADENNO");
		list.add("TR_SYUKKA_MEISAI.SYUKKADENMEISAIID");
		list.add("TR_SYUKKA_MEISAI.SYUKKADENMEISAINO");
		list.add("TR_SYUHIKIATDNMEIBTUSURYO.SHOHINID");
		list.add("MS_SHOHIN.SHOHINCD");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("MS_SHOHIN.SHOHINNM");
		list.add("MS_SHOHIN.SHOHINRKNM");
		list.add("MS_SHOHIN.COLORKBN");
		list.add("MVMS_KBNTI_WMS.KBNTINM1");
		list.add("MS_SHOHIN.SIZEKBN");
		list.add("MVMS_KBNTI_WMS.KBNTINM1");
		list.add("MS_SHOHIN.KIKAKU");
		list.add("TR_SYUKKA_DEN.TORIHIKISAKIID_NOHINSAKI");
		list.add("TR_SYUKKA_DEN.TORIHIKISAKIID_NOHINSAKI = nullの場合 TR_SYUKKA_DEN.TORIHIKISAKICD_NOHINSAKI それ以外の場合 MS_TORIHIKISAKI.TORIHIKISAKICD");
		list.add("TR_SYUKKA_DEN.TORIHIKISAKIID_NOHINSAKI = nullの場合 TR_SYUKKA_DEN.TORIHIKISAKINM_NOHINSAKI それ以外の場合 MS_TORIHIKISAKI.TORIHIKISAKINM");
		list.add("TR_SYUKKA_DEN.TORIHIKISAKIID_NOHINSAKI = nullの場合 TR_SYUKKA_DEN.TORIHIKISAKIRKNM_NOHINSAKI それ以外の場合 MS_TORIHIKISAKI.TORIHIKISAKIRKNM");
		list.add("TR_SYUKKA_DEN.TORIHIKISAKIID_NOHINSAKI = nullの場合 TR_SYUKKA_DEN.TEL_NOHINSAKI それ以外の場合 MS_TORIHIKISAKI.TORIHIKISAKITEL");
		list.add("TR_SYUKKA_DEN.TORIHIKISAKIID_NOHINSAKI = nullの場合 TR_SYUKKA_DEN.YUBIN_NOHINSAKI それ以外の場合 MS_TORIHIKISAKI.YUBIN");
		list.add("TR_SYUKKA_DEN.TORIHIKISAKIID_NOHINSAKI = nullの場合 TR_SYUKKA_DEN.KUNICD_NOHINSAKI それ以外の場合 MS_TORIHIKISAKI.KUNICD");
		list.add("TR_SYUKKA_DEN.TORIHIKISAKIID_NOHINSAKI = nullの場合 TR_SYUKKA_DEN.JISCD_NOHINSAKI それ以外の場合 MS_TORIHIKISAKI.JISCD");
		list.add("TR_SYUKKA_DEN.OLDJYUSYO_NOHINSAKI1");
		list.add("TR_SYUKKA_DEN.OLDJYUSYO_NOHINSAKI2");
		list.add("TR_SYUKKA_DEN.OLDJYUSYO_NOHINSAKI3");
		list.add("TR_SYUKKA_DEN.TORIHIKISAKIID_NOHINSAKI = nullの場合 TR_SYUKKA_DEN.TORIHIKISAKIJYUSYO_NOHINSAKI1 それ以外の場合 MS_TORIHIKISAKI.TORIHIKISAKIJYUSYO1");
		list.add("TR_SYUKKA_DEN.TORIHIKISAKIID_NOHINSAKI = nullの場合 TR_SYUKKA_DEN.TORIHIKISAKIJYUSYO_NOHINSAKI2 それ以外の場合 MS_TORIHIKISAKI.TORIHIKISAKIJYUSYO2");
		list.add("TR_SYUKKA_DEN.TORIHIKISAKIID_NOHINSAKI = nullの場合 TR_SYUKKA_DEN.TORIHIKISAKIJYUSYO_NOHINSAKI3 それ以外の場合 MS_TORIHIKISAKI.TORIHIKISAKIJYUSYO3");
		list.add("TR_SYUHIKIATDNMEIBTUSURYO.HINSITUKBN");
		list.add("MVMS_KBNTI_WMS.KBNTINM1");
		list.add("TR_SYUHIKIATDNMEIBTUSURYO.YOTEISURYO");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("TR_SYUHIKIATDNMEIBTUSURYO.HIKIATESURYO");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("TR_SYUHIKIATDNMEIBTUSURYO.HIKIATEKEPPINSURYO");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("TR_SYUHIKIATDNMEIBTUSURYO.HOJYUKANOSURYO");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("TR_SYUHIKIATDNMEIBTUSURYO.HIKIATEKANOSURYO");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("NULL");
		list.add("NULL");
		list.add("NULL");
		list.add("INパラメータ/*torokuusr*/");
		list.add("INパラメータ/*torokutrm*/");
		list.add("SYSDATE");
		list.add("INパラメータ/*exclcnt*/'0'");
		list.add("INパラメータ/*torokudate*/sysdate");
		list.add("INパラメータ/*torokuusr*/");
		list.add("INパラメータ/*torokutrm*/");
		list.add("INパラメータ/*torokuprgrm*/");
		list.add("INパラメータ/*kosindate*/sysdate");
		list.add("INパラメータ/*kosinusr*/");
		list.add("INパラメータ/*kosintrm*/");
		list.add("INパラメータ/*kosinprgrm*/");

		list.stream().forEach(element -> {
			String temp = element;
			for (String key : map.keySet()) {
				if (temp.contains(key)) {
					temp = temp.replaceAll(key, map.get(key));
				}
			}

			System.out.println(temp);
		});
	}
}
