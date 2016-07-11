package com.github.kennethnickles.gatherer.card;

import android.util.Log;
import com.github.kennethnickles.gatherer.util.CardGsonFactory;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * @author kenneth.nickles
 * @since 2016-04-04.
 */
public class SetDeserializerTest {

	@org.junit.Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	Log log;

	@Test
	public void sets() throws Exception {
		final InputStream inputStream = getResourceAsStream("sets.json", this);
		final JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
		final Gson gson = new CardGsonFactory().createCardGson();
		final Set[] sets = gson.fromJson(jsonReader, Set[].class);
		Assert.assertNotNull(sets);
		Assert.assertEquals(194, sets.length);
	}

	@Test
	public void secondEdition() throws Exception {
		Assert.assertEquals(292, parseCards("2ED.json").length);
	}

	@Test
	public void thirdEdition() throws Exception {
		Assert.assertEquals(296, parseCards("3ED.json").length);
	}

	@Test
	public void fourthEdition() throws Exception {
		Assert.assertEquals(368, parseCards("4ED.json").length);
	}

	@Test
	public void fifthDawn() throws Exception {
		Assert.assertEquals(165, parseCards("5DN.json").length);
	}

	@Test
	public void fifthEdition() throws Exception {
		Assert.assertEquals(434, parseCards("5ED.json").length);
	}

	@Test
	public void sixthEdition() throws Exception {
		Assert.assertEquals(335, parseCards("6ED.json").length);
	}

	@Test
	public void seventhEdition() throws Exception {
		Assert.assertEquals(335, parseCards("7ED.json").length);
	}

	@Test
	public void eigthEdition() throws Exception {
		Assert.assertEquals(342, parseCards("8ED.json").length);
	}

	@Test
	public void ninthEdition() throws Exception {
		Assert.assertEquals(344, parseCards("9ED.json").length);
	}

	@Test
	public void tenthEdition() throws Exception {
		Assert.assertEquals(368, parseCards("10E.json").length);
	}

	@Test
	public void shardsOfAlara() throws Exception {
		Assert.assertEquals(234, parseCards("ALA.json").length);
	}

	@Test
	public void alliance() throws Exception {
		Assert.assertEquals(144, parseCards("ALL.json").length);
	}

	@Test
	public void apocalypse() throws Exception {
		Assert.assertEquals(148, parseCards("APC.json").length);
	}

	@Test
	public void alaraReborn() throws Exception {
		Assert.assertEquals(145, parseCards("ARB.json").length);
	}

	@Test
	public void archenemy() throws Exception {
		Assert.assertEquals(187, parseCards("ARC.json").length);
	}

	@Test
	public void arabianNights() throws Exception {
		Assert.assertEquals(78, parseCards("ARN.json").length);
	}

	@Test
	public void anthologies() throws Exception {
		Assert.assertEquals(81, parseCards("ATH.json").length);
	}

	@Test
	public void antiquities() throws Exception {
		Assert.assertEquals(85, parseCards("ATQ.json").length);
	}

	@Test
	public void avacynRestored() throws Exception {
		Assert.assertEquals(234, parseCards("AVR.json").length);
	}

	@Test
	public void battleForZendikar() throws Exception {
		Assert.assertEquals(254, parseCards("BFZ.json").length);
	}

	@Test
	public void bornOfTheGods() throws Exception {
		Assert.assertEquals(165, parseCards("BNG.json").length);
	}

	@Test
	public void betrayersOfKamigawa() throws Exception {
		Assert.assertEquals(170, parseCards("BOK.json").length);
	}

	@Test
	public void battleRoyaleBoxSet() throws Exception {
		Assert.assertEquals(105, parseCards("BRB.json").length);
	}

	@Test
	public void beatdownBoxSet() throws Exception {
		Assert.assertEquals(82, parseCards("BTD.json").length);
	}

	@Test
	public void duelDecksBlessedVsCursed1() throws Exception {
		Assert.assertEquals(0, parseCards("BVC.json").length);
	}

	@Test
	public void commander13() throws Exception {
		Assert.assertEquals(342, parseCards("C13.json").length);
	}

	@Test
	public void commander14() throws Exception {
		Assert.assertEquals(322, parseCards("C14.json").length);
	}

	@Test
	public void commander15() throws Exception {
		Assert.assertEquals(327, parseCards("C15.json").length);
	}

	@Test
	public void collectorsEdition() throws Exception {
		Assert.assertEquals(292, parseCards("CED.json").length);
	}

	@Test
	public void collectorsEditionInternational() throws Exception {
		Assert.assertEquals(292, parseCards("CEI.json").length);
	}

	@Test
	public void championsOfKamigawa() throws Exception {
		Assert.assertEquals(301, parseCards("CHK.json").length);
	}

	@Test
	public void chronicles() throws Exception {
		Assert.assertEquals(116, parseCards("CHR.json").length);
	}

	@Test
	public void commandersArsenal() throws Exception {
		Assert.assertEquals(18, parseCards("CM1.json").length);
	}

	@Test
	public void commander() throws Exception {
		Assert.assertEquals(305, parseCards("CMD.json").length);
	}

	@Test
	public void conspiracy() throws Exception {
		Assert.assertEquals(210, parseCards("CNS.json").length);
	}

	@Test
	public void conflux() throws Exception {
		Assert.assertEquals(145, parseCards("CON.json").length);
	}

	@Test
	public void clashPack() throws Exception {
		Assert.assertEquals(12, parseCards("CPK.json").length);
	}

	@Test
	public void coldsnap() throws Exception {
		Assert.assertEquals(155, parseCards("CSP.json").length);
	}

	@Test
	public void coldsnapThemeDecks() throws Exception {
		Assert.assertEquals(52, parseCards("CST.json").length);
	}

	@Test
	public void duelDecksJaceVsChandra() throws Exception {
		Assert.assertEquals(57, parseCards("DD2.json").length);
	}

	@Test
	public void duelDecksAnthologyDivineVsDemonic() throws Exception {
		Assert.assertEquals(56, parseCards("DD3_DVD.json").length);
	}

	@Test
	public void duelDecksAnthologyElvesVsGoblins() throws Exception {
		Assert.assertEquals(56, parseCards("DD3_EVG.json").length);
	}

	@Test
	public void duelDecksAnthologyGarrukVsLiliana() throws Exception {
		Assert.assertEquals(57, parseCards("DD3_GVL.json").length);
	}

	@Test
	public void duelDecksAnthologyJaceVsChandra() throws Exception {
		Assert.assertEquals(56, parseCards("DD3_JVC.json").length);
	}

	@Test
	public void duelDecksDivineVsDemonic() throws Exception {
		Assert.assertEquals(59, parseCards("DDC.json").length);
	}

	@Test
	public void duelDecksGarrukVsLiliana() throws Exception {
		Assert.assertEquals(59, parseCards("DDD.json").length);
	}

	@Test
	public void duelDecksPhyrexiaVsTheCoalition() throws Exception {
		Assert.assertEquals(70, parseCards("DDE.json").length);
	}

	@Test
	public void duelDecksElspethVsTezzeret() throws Exception {
		Assert.assertEquals(73, parseCards("DDF.json").length);
	}

	@Test
	public void duelDecksKnightsVsDragons() throws Exception {
		Assert.assertEquals(72, parseCards("DDG.json").length);
	}

	@Test
	public void duelDecksAjaniVsNicolBolas() throws Exception {
		Assert.assertEquals(80, parseCards("DDH.json").length);
	}

	@Test
	public void duelDecksVenserVsKoth() throws Exception {
		Assert.assertEquals(70, parseCards("DDI.json").length);
	}

	@Test
	public void duelDecksIzzetVsGolgari() throws Exception {
		Assert.assertEquals(80, parseCards("DDJ.json").length);
	}

	@Test
	public void duelDecksSorinVsTibalt() throws Exception {
		Assert.assertEquals(71, parseCards("DDK.json").length);
	}

	@Test
	public void duelDecksHeroesVsMonsters() throws Exception {
		Assert.assertEquals(68, parseCards("DDL.json").length);
	}

	@Test
	public void duelDecksJaceVsVraska() throws Exception {
		Assert.assertEquals(76, parseCards("DDM.json").length);
	}

	@Test
	public void duelDecksSpeedVsCunning() throws Exception {
		Assert.assertEquals(72, parseCards("DDN.json").length);
	}

	@Test
	public void duelDecksElspethVsKiora() throws Exception {
		Assert.assertEquals(60, parseCards("DDO.json").length);
	}

	@Test
	public void duelDecksZendikarVsEldrazi() throws Exception {
		Assert.assertEquals(70, parseCards("DDP.json").length);
	}

	@Test
	public void duelDecksBlessedVsCursed2() throws Exception {
		Assert.assertEquals(71, parseCards("DDQ.json").length);
	}

	@Test
	public void dragonsMaze() throws Exception {
		Assert.assertEquals(171, parseCards("DGM.json").length);
	}

	@Test
	public void dissension() throws Exception {
		Assert.assertEquals(190, parseCards("DIS.json").length);
	}

	@Test
	public void darkAscension() throws Exception {
		Assert.assertEquals(171, parseCards("DKA.json").length);
	}

	@Test
	public void deckmasters() throws Exception {
		Assert.assertEquals(44, parseCards("DKM.json").length);
	}

	@Test
	public void duelsOfThePlaneswalkers() throws Exception {
		Assert.assertEquals(100, parseCards("DPA.json").length);
	}

	@Test
	public void fromTheVaultsDragons() throws Exception {
		Assert.assertEquals(15, parseCards("DRB.json").length);
	}

	@Test
	public void theDark() throws Exception {
		Assert.assertEquals(119, parseCards("DRK.json").length);
	}

	@Test
	public void darksteel() throws Exception {
		Assert.assertEquals(165, parseCards("DST.json").length);
	}

	@Test
	public void dragonsOfTarkir() throws Exception {
		Assert.assertEquals(254, parseCards("DTK.json").length);
	}

	@Test
	public void eternalMasters() throws Exception {
		Assert.assertEquals(249, parseCards("EMA.json").length);
	}

	@Test
	public void eventide() throws Exception {
		Assert.assertEquals(180, parseCards("EVE.json").length);
	}

	@Test
	public void duelDecksElvesVsGoblins() throws Exception {
		Assert.assertEquals(59, parseCards("EVG.json").length);
	}

	@Test
	public void exodus() throws Exception {
		Assert.assertEquals(143, parseCards("EXO.json").length);
	}

	@Test
	public void zendikarExpedition() throws Exception {
		Assert.assertEquals(45, parseCards("EXP.json").length);
	}

	@Test
	public void fallenEmpires() throws Exception {
		Assert.assertEquals(102, parseCards("FEM.json").length);
	}

	@Test
	public void fateReforged() throws Exception {
		Assert.assertEquals(180, parseCards("FRF.json").length);
	}

	@Test
	public void promoUginsFate() throws Exception {
		Assert.assertEquals(26, parseCards("FRF_UGIN.json").length);
	}

	@Test
	public void futureSight() throws Exception {
		Assert.assertEquals(180, parseCards("FUT.json").length);
	}

	@Test
	public void guildpact() throws Exception {
		Assert.assertEquals(165, parseCards("GPT.json").length);
	}

	@Test
	public void gatecrash() throws Exception {
		Assert.assertEquals(249, parseCards("GTC.json").length);
	}

	@Test
	public void premiumDeckSeriesSlivers() throws Exception {
		Assert.assertEquals(41, parseCards("H09.json").length);
	}

	@Test
	public void homelands() throws Exception {
		Assert.assertEquals(115, parseCards("HML.json").length);
	}

	@Test
	public void planechase() throws Exception {
		Assert.assertEquals(189, parseCards("HOP.json").length);
	}

	@Test
	public void iceage() throws Exception {
		Assert.assertEquals(373, parseCards("ICE.json").length);
	}

	@Test
	public void invasion() throws Exception {
		Assert.assertEquals(340, parseCards("INV.json").length);
	}

	@Test
	public void innistrad() throws Exception {
		Assert.assertEquals(274, parseCards("ISD.json").length);
	}

	@Test
	public void introductoryTwoPlayerSet() throws Exception {
		Assert.assertEquals(57, parseCards("ITP.json").length);
	}

	@Test
	public void journeyIntoNyx() throws Exception {
		Assert.assertEquals(165, parseCards("JOU.json").length);
	}

	@Test
	public void judgement() throws Exception {
		Assert.assertEquals(143, parseCards("JUD.json").length);
	}

	@Test
	public void khansOfTarkir() throws Exception {
		Assert.assertEquals(254, parseCards("KTK.json").length);
	}

	@Test
	public void limitedEditionAlpha() throws Exception {
		Assert.assertEquals(290, parseCards("LEA.json").length);
	}

	@Test
	public void limitedEditionBeta() throws Exception {
		Assert.assertEquals(292, parseCards("LEB.json").length);
	}

	@Test
	public void legions() throws Exception {
		Assert.assertEquals(310, parseCards("LEG.json").length);
	}

	@Test
	public void legends() throws Exception {
		Assert.assertEquals(145, parseCards("LGN.json").length);
	}

	@Test
	public void lorwyn() throws Exception {
		Assert.assertEquals(286, parseCards("LRW.json").length);
	}

	@Test
	public void m10() throws Exception {
		Assert.assertEquals(234, parseCards("M10.json").length);
	}

	@Test
	public void m11() throws Exception {
		Assert.assertEquals(234, parseCards("M11.json").length);
	}

	@Test
	public void m12() throws Exception {
		Assert.assertEquals(234, parseCards("M12.json").length);
	}

	@Test
	public void m13() throws Exception {
		Assert.assertEquals(234, parseCards("M13.json").length);
	}

	@Test
	public void m14() throws Exception {
		Assert.assertEquals(234, parseCards("M14.json").length);
	}

	@Test
	public void m15() throws Exception {
		Assert.assertEquals(269, parseCards("M15.json").length);
	}

	@Test
	public void mirrodinBesieged() throws Exception {
		Assert.assertEquals(150, parseCards("MBS.json").length);
	}

	@Test
	public void modernEventDeck2014() throws Exception {
		Assert.assertEquals(26, parseCards("MD1.json").length);
	}

	@Test
	public void mastersEditionII() throws Exception {
		Assert.assertEquals(245, parseCards("ME2.json").length);
	}

	@Test
	public void mastersEditionIII() throws Exception {
		Assert.assertEquals(220, parseCards("ME3.json").length);
	}

	@Test
	public void mastersEditionIV() throws Exception {
		Assert.assertEquals(260, parseCards("ME4.json").length);
	}

	@Test
	public void mastersEdition() throws Exception {
		Assert.assertEquals(185, parseCards("MED.json").length);
	}

	@Test
	public void multiverseGiftBox() throws Exception {
		Assert.assertEquals(10, parseCards("MGB.json").length);
	}

	@Test
	public void mirage() throws Exception {
		Assert.assertEquals(335, parseCards("MIR.json").length);
	}

	@Test
	public void modernMasters2015Edition() throws Exception {
		Assert.assertEquals(249, parseCards("MM2.json").length);
	}

	@Test
	public void modernMasters() throws Exception {
		Assert.assertEquals(229, parseCards("MMA.json").length);
	}

	@Test
	public void mercadianMasques() throws Exception {
		Assert.assertEquals(335, parseCards("MMQ.json").length);
	}

	@Test
	public void morningtide() throws Exception {
		Assert.assertEquals(150, parseCards("MOR.json").length);
	}

	@Test
	public void mirrodin() throws Exception {
		Assert.assertEquals(291, parseCards("MRD.json").length);
	}

	@Test
	public void nemesis() throws Exception {
		Assert.assertEquals(143, parseCards("NMS.json").length);
	}

	@Test
	public void newPhyrexia() throws Exception {
		Assert.assertEquals(170, parseCards("NPH.json").length);
	}

	@Test
	public void odyssey() throws Exception {
		Assert.assertEquals(335, parseCards("ODY.json").length);
	}

	@Test
	public void oathOfTheGatewatch() throws Exception {
		Assert.assertEquals(183, parseCards("OGW.json").length);
	}

	@Test
	public void onslaught() throws Exception {
		Assert.assertEquals(335, parseCards("ONS.json").length);
	}

	@Test
	public void origins() throws Exception {
		Assert.assertEquals(278, parseCards("ORI.json").length);
	}

	@Test
	public void promoTwoHeadedGiant() throws Exception {
		Assert.assertEquals(1, parseCards("p2HG.json").length);
	}

	@Test
	public void promo15A() throws Exception {
		Assert.assertEquals(2, parseCards("p15A.json").length);
	}

	@Test
	public void promoALP() throws Exception {
		Assert.assertEquals(5, parseCards("pALP.json").length);
	}

	@Test
	public void promoARL() throws Exception {
		Assert.assertEquals(46, parseCards("pARL.json").length);
	}

	@Test
	public void planechase2012Edition() throws Exception {
		Assert.assertEquals(177, parseCards("PC2.json").length);
	}

	@Test
	public void promoCEL() throws Exception {
		Assert.assertEquals(6, parseCards("pCEL.json").length);
	}

	@Test
	public void promoCMP() throws Exception {
		Assert.assertEquals(12, parseCards("pCMP.json").length);
	}

	@Test
	public void prophecy() throws Exception {
		Assert.assertEquals(143, parseCards("PCY.json").length);
	}

	@Test
	public void premiumDeckSeriesFireAndLightning() throws Exception {
		Assert.assertEquals(31, parseCards("PD2.json").length);
	}

	@Test
	public void premiumDeckSeriesGraveborn() throws Exception {
		Assert.assertEquals(27, parseCards("PD3.json").length);
	}

	@Test
	public void promoDragonCon() throws Exception {
		Assert.assertEquals(1, parseCards("pDRC.json").length);
	}

	@Test
	public void promoEuropeanLandProgram() throws Exception {
		Assert.assertEquals(5, parseCards("pELP.json").length);
	}

	@Test
	public void promoFridayNightMagic() throws Exception {
		Assert.assertEquals(193, parseCards("pFNM.json").length);
	}

	@Test
	public void promoGrandPrix() throws Exception {
		Assert.assertEquals(11, parseCards("pGPX.json").length);
	}

	@Test
	public void promoGuru() throws Exception {
		Assert.assertEquals(5, parseCards("pGRU.json").length);
	}

	@Test
	public void promoGateway() throws Exception {
		Assert.assertEquals(20, parseCards("pGTW.json").length);
	}

	@Test
	public void promoHappyHolidays() throws Exception {
		Assert.assertEquals(10, parseCards("pHHO.json").length);
	}

	@Test
	public void promoJudgeGiftProgram() throws Exception {
		Assert.assertEquals(98, parseCards("pJGP.json").length);
	}

	@Test
	public void planarChaos() throws Exception {
		Assert.assertEquals(168, parseCards("PLC.json").length);
	}

	@Test
	public void promoLegendMembership() throws Exception {
		Assert.assertEquals(2, parseCards("pLGM.json").length);
	}

	@Test
	public void promoLaunchParties() throws Exception {
		Assert.assertEquals(33, parseCards("pLPA.json").length);
	}

	@Test
	public void planeshift() throws Exception {
		Assert.assertEquals(143, parseCards("PLS.json").length);
	}

	@Test
	public void promoMediaInsert() throws Exception {
		Assert.assertEquals(146, parseCards("pMEI.json").length);
	}

	@Test
	public void promoMagicGameday() throws Exception {
		Assert.assertEquals(50, parseCards("pMGD.json").length);
	}

	@Test
	public void promoMagicPlayerRewards() throws Exception {
		Assert.assertEquals(53, parseCards("pMPR.json").length);
	}

	@Test
	public void portalSecondAge() throws Exception {
		Assert.assertEquals(155, parseCards("PO2.json").length);
	}

	@Test
	public void portal() throws Exception {
		Assert.assertEquals(200, parseCards("POR.json").length);
	}

	@Test
	public void promoPortalDemoGame() throws Exception {
		Assert.assertEquals(6, parseCards("pPOD.json").length);
	}

	@Test
	public void promoPreReleaseEvent() throws Exception {
		Assert.assertEquals(307, parseCards("pPRE.json").length);
	}

	@Test
	public void promoProTour() throws Exception {
		Assert.assertEquals(5, parseCards("pPRO.json").length);
	}

	@Test
	public void promoReleaseEvent() throws Exception {
		Assert.assertEquals(14, parseCards("pREL.json").length);
	}

	@Test
	public void promoSummerOfMagic() throws Exception {
		Assert.assertEquals(2, parseCards("pSUM.json").length);
	}

	@Test
	public void promoSuperSeries() throws Exception {
		Assert.assertEquals(18, parseCards("pSUS.json").length);
	}

	@Test
	public void portalThreeKingdoms() throws Exception {
		Assert.assertEquals(170, parseCards("PTK.json").length);
	}

	@Test
	public void promoWorldCupQualifiers() throws Exception {
		Assert.assertEquals(2, parseCards("pWCQ.json").length);
	}

	@Test
	public void promoWorlds() throws Exception {
		Assert.assertEquals(1, parseCards("pWOR.json").length);
	}

	@Test
	public void promoWizardsOfTheCoastOnlineStore() throws Exception {
		Assert.assertEquals(1, parseCards("pWOS.json").length);
	}

	@Test
	public void promoWPNAndGateway() throws Exception {
		Assert.assertEquals(43, parseCards("pWPN.json").length);
	}

	@Test
	public void ravnicaCityOfGuilds() throws Exception {
		Assert.assertEquals(291, parseCards("RAV.json").length);
	}

	@Test
	public void riseOfEldrazi() throws Exception {
		Assert.assertEquals(233, parseCards("ROE.json").length);
	}

	@Test
	public void rivalsQuickStartSet() throws Exception {
		Assert.assertEquals(54, parseCards("RQS.json").length);
	}

	@Test
	public void returnToRavnica() throws Exception {
		Assert.assertEquals(254, parseCards("RTR.json").length);
	}

	@Test
	public void starter2000() throws Exception {
		Assert.assertEquals(53, parseCards("S00.json").length);
	}

	@Test
	public void starter1999() throws Exception {
		Assert.assertEquals(158, parseCards("S99.json").length);
	}

	@Test
	public void scourge() throws Exception {
		Assert.assertEquals(143, parseCards("SCG.json").length);
	}

	@Test
	public void shadowmoor() throws Exception {
		Assert.assertEquals(286, parseCards("SHM.json").length);
	}

	@Test
	public void shadowOverInnistrad() throws Exception {
		Assert.assertEquals(320, parseCards("SOI.json").length);
	}

	@Test
	public void saviorsOfKamigawa() throws Exception {
		Assert.assertEquals(170, parseCards("SOK.json").length);
	}

	@Test
	public void scarsOfMirrodin() throws Exception {
		Assert.assertEquals(234, parseCards("SOM.json").length);
	}

	@Test
	public void stronghold() throws Exception {
		Assert.assertEquals(143, parseCards("STH.json").length);
	}

	@Test
	public void theros() throws Exception {
		Assert.assertEquals(234, parseCards("THS.json").length);
	}

	@Test
	public void tempest() throws Exception {
		Assert.assertEquals(335, parseCards("TMP.json").length);
	}

	@Test
	public void torment() throws Exception {
		Assert.assertEquals(143, parseCards("TOR.json").length);
	}

	@Test
	public void tempestRemastered() throws Exception {
		Assert.assertEquals(254, parseCards("TPR.json").length);
	}

	@Test
	public void timeSpiralTimeShifted() throws Exception {
		Assert.assertEquals(122, parseCards("TSB.json").length);
	}

	@Test
	public void timeSpiral() throws Exception {
		Assert.assertEquals(286, parseCards("TSP.json").length);
	}

	@Test
	public void urzasDestiny() throws Exception {
		Assert.assertEquals(143, parseCards("UDS.json").length);
	}

	@Test
	public void unglued() throws Exception {
		Assert.assertEquals(93, parseCards("UGL.json").length);
	}

	@Test
	public void urzasLegacy() throws Exception {
		Assert.assertEquals(143, parseCards("ULG.json").length);
	}

	@Test
	public void unhinged() throws Exception {
		Assert.assertEquals(145, parseCards("UNH.json").length);
	}

	@Test
	public void urzasSaga() throws Exception {
		Assert.assertEquals(335, parseCards("USG.json").length);
	}

	@Test
	public void fromTheVaultExiled() throws Exception {
		Assert.assertEquals(15, parseCards("V09.json").length);
	}

	@Test
	public void fromTheVaultRelics() throws Exception {
		Assert.assertEquals(15, parseCards("V10.json").length);
	}

	@Test
	public void fromTheVaultLegends() throws Exception {
		Assert.assertEquals(15, parseCards("V11.json").length);
	}

	@Test
	public void fromTheVaultRealms() throws Exception {
		Assert.assertEquals(15, parseCards("V12.json").length);
	}

	@Test
	public void fromTheVaultTwenty() throws Exception {
		Assert.assertEquals(20, parseCards("V13.json").length);
	}

	@Test
	public void fromTheVaultAnnihilation() throws Exception {
		Assert.assertEquals(15, parseCards("V14.json").length);
	}

	@Test
	public void fromTheVaultAngels() throws Exception {
		Assert.assertEquals(15, parseCards("V15.json").length);
	}

	@Test
	public void vanguard() throws Exception {
		Assert.assertEquals(106, parseCards("VAN.json").length);
	}

	@Test
	public void visions() throws Exception {
		Assert.assertEquals(167, parseCards("VIS.json").length);
	}

	@Test
	public void vintageMasters() throws Exception {
		Assert.assertEquals(325, parseCards("VMA.json").length);
	}

	@Test
	public void welcomeDeck2016() throws Exception {
		Assert.assertEquals(16, parseCards("W16.json").length);
	}

	@Test
	public void weatherLight() throws Exception {
		Assert.assertEquals(167, parseCards("WTH.json").length);
	}

	@Test
	public void worldwake() throws Exception {
		Assert.assertEquals(145, parseCards("WWK.json").length);
	}

	@Test
	public void zendikar() throws Exception {
		Assert.assertEquals(234, parseCards("ZEN.json").length);
	}

	private Card[] parseCards(String jsonFile) throws Exception {
		final InputStream inputStream = getResourceAsStream(jsonFile, this);
		final JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
		final Gson gson = new CardGsonFactory().createCardGson();
		return gson.fromJson(jsonReader, Card[].class);
	}

	private static InputStream getResourceAsStream(String fileName, Object source) {
		return source.getClass().getResourceAsStream(fileName);
	}
}
