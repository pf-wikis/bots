package io.github.pfwikis.bots.replacer;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class Replacer extends SimpleBot {

	public Replacer() {
		super("replacer", "Bot Manual Bulk Operations");
	}
	
	@Override
	protected String getDescription() {
		return "This bot is only started by hand for manual bulk changes to the wiki.";
	}

	@Override
	public void run() throws IOException {	
		//replaceWrongCitations();
		
		//replaceCitations();
		
		//removeOldCiteBooks();
		
		var pages = List.of(
				"Ukara","Skyfire Mandate","Preita","Kamora","Allied Territories","Drakelands","Wailing Stone","Refuge","Refugists","Doyenate","Sholar Adat","Pradulex Monastery","Outlander","Alsuka's Tea House","Zyphus","Lambatuin","Cayden Cailean","Cavrabon","Accelsys","Shelynium","Outlaw Kingdoms","Grand Assembly","Tidal locking","Ring of Nations","Thasteron","Syndicsguild","Strong Absalom","Laubu Mesa","Sovyrian","Aiudara","Automatrix Robotics","Those Who Become","Those Who Wait","Insight Array","Robot","Machine Court","Abaddon","Plane of Water","Plane of Earth","Plane of Air","Positive Energy Plane","First World","Nethys","Disease","First contact","Ethereal Plane","Elemental Plane","Astral Plane","Great Beyond","Plane","Signal","Chaos Wyrm","Hybrid item","First Ones","Cephalume","Tromlin","Prismeni","Ganzi","Kobold","Kaa-leki","Gathol","Espraksa","Dirindi","Amrantah","Playable Species","Izadamar","Ternia","Desna's Path","Boojan","Alsuka","Sovereign Trinity","Cognates","Kortus IV","Ragadahn","Architects","Negative Energy Plane","Kehtaria","ChiwaTech","Guardian Tree","Alignment singularity","Romgul","Pazuzu","Facilian","Tyru","Lesser Confusion","Confusion","Greater Command","Charm Monster","Command","Charm Person","Daze","Strunun","Yormata","Orugan","Locus-1","Locus","Quatherat Hafet","Nairon Shalorrh","Gaileia Seeoh","Esax Jana Meritus Rhee","Nasenlir Zye Fassar","Salasari Mou Qari","Imma Elotok Hin Xogathu","Yuki Utsama","Barasul Naedarin Allar","Enchantment","Ibra's Aurora","Pemano Teth","Shazzag","Zukar Nurkop","Vahal Ayos","Halls of Reason","Landahl","Sea of Glass","Ghavaniska system","Marixah Republic","Szandite Collective","Multiverse","Skadia","Jularaz","Great Hall of the Conqueror","Morgebard","Tier-38-Mentor","Eclipse Academy","Sopeth Corporation","Midnight Trenches","Occhiorasoi","Pursuit","Atherm","Church of the Burning Mother","Trafodi Paradox","Jatembe Park","Zo! Media","Chiskisk","Moyishuu","Kreiholm Freehold","Eshki","Thromkendal","Khefak Depot","Hilo Beruta","Zafeldrin","Smuggler's Moon","Weydana-4","Archonomix","Forgeworks Innovations","Arioch-Oyadae","Calaphidion","Entha","Aglian","Hoarboar","Riyethira","Sondria","Entu","Glaiad","Wyspiria","Many-miened one","Vortex dragon","Plasma dragon","Cryo dragon","Laser dragon","Planashar","Lucandrian","Voraija","Shard diver","Erabryth","Endling","Claustral","Projectile dragon","Gold dragon","Horacalcum dragon","Noqual dragon","Red dragon","White dragon","Green dragon","Blue dragon","Black dragon","Vicunal","Durgastr","Alorbidaemon","Alucidaemon","Morrigna","Hivemarket","Sivv","Aqlath","Sage-coffer","Mahadatari","Brood sovereign","Exodus angel","Exhaust ooze","Tapestry moth","Toshigami","Limina","Nacrea","Exsiccate","Avigwyr","Khulan","Adikodaemon","Starward","Union City","Umbracygot","Death cruiser","Spookfish","Hungerer from the dark","Driftlure","Gray shell","Cerebrex","Driftmaven","Guardian Rhino","Derelict shade","Citadel Zirval","Dormakhabu","Xiritix","Alinoisos","Dycepskian elder brain","Lawless one","Tudine","Dycepskian","Zernivian","Keji","Night hag","Linyf","Kunnid","Depot 583-B","Psydrake","Glacial borer","Shadow hopper","Megalonyxa","Reptoid hybrid","Wetzelt","Pakahano","Vesk-5.1","Pabaq","Vesk-7","Vesk-4","Varturan","Stopgap","Scouring swarm","Troll polyp","Replicant ooze","Quantum troll","Void troll","Gray bounder","Vesk-6","Helfen","Nemenar","Vesk-3","Gideron Prime","Marixah","Gaskar III","Ghorus Prime","Vesk-8","Ulthel","Vesk-2","Pholskar","Conqueror's Forge","Command 4","Starlance","Vesk-5","Command 3","Forget","Command 2","Spaceport 5.1","Command Prime","Abazobari family","Laoe Araae","Division of Disloyal Organizations","Incapacitator robot","Gadravel","Proog","Cargo creep","Aerial devil","Barghest","Aanung-an","Spirit oni","Cerulean Order","Brenneri","Hobkins","Cosmonastery of the Empty Orbit","Centus II","Zeffrac Science Platform","Fulmivar","Seed walker","Xararian","Virtual phantom","Psychovox","Broodsoul","Programmer","Gnawbore","Memetic zenith","Echidea","God-Host","Paraforan","Guardian robot","Spathinae","Jakkerant","Jioh Station","Vorthuul","Kothama","Revulsor","Dilnefa","Dina III","Valnarum","Muneen","Swarm Patrol Corps","Psychic abomination","Yotuhn","Chonax","Songbird Station","Mindscape","Impostor","Ceretoram","Endeavor","Dessamar","Hortus","Yithian","Rakmos","Ramiya","New Valor","Stelezorn","Vahdu","Tumbletooth","Feeder fungus","Gideron Authority","Xio","Frozen Trove Labs","Maurnak","Portalseers","Plane of Fire","Kelldor Memorial Center for Personal Enrichment","Stroxha","Songtwisted","Quandali","Vathori","Effigene","Prodigal Stone","Trinir","Atachuuva","Antecursor","Ashypsozoan","Molitera","Flashfire demon","Ashypso","Dramovire","Remorhaz","Stabrisis-14","Amran","Altipheron","Grascha","Escutchide","Throkhia","Gluun","Vharrine","Tarchuuva","Ilemchuuva","Dhurus","Kiirinta","Hound of Tindalos","Amity Initiative","Orzaka Institute of Swarm Biology","Nauphage","Ijtikri","Utraneus","Drift architect","Gleerick","Shatori","Rendalairn","Disciples of Grace","Troll","Philosopher worm","Aspecna","Keepers of the Lie","Time eater","Auroric Palace","Neotophet","Nanobot mimic","Statikete","Iridia","Stridermander","Chinjugami","Varculak","Gwahled","Suskillon","Xersk","Dissolver","Convecyte","Vorphoma","Dredger","Fleetfury","Rheonnaghan","Spectra","Onkushu","Marid","Undine","City of Brass","Halls of the Living","Shae","Scales of the Conqueror","Lights of Nhimbaloth","Croban system","Quinander system","Arkenal","Xulxede","Chomurk","Plistarn","Cynosure","Kahlannal","Estonar","Shaitan","Xaxmellia","Lislaroth","Lantanirian","Anassanoi","Oread","Grindhold","Anyalaritus","Dahak's Claw","Vimal","Fastness of the Ordered Mind","Verdant Shield","Those Who Create","Forgiven","Church of Silence","Security Resources Pavilion","Bluerise Tower","Ixo Syndicate","Magma ooze","Magmin","Holy Angel of Flame","Ataxxea","Pyric heliacus","Ezorod","Pyric wraith","Astevint","Dimensional shambler","Pyric revenant","Pyric harbinger","Maro","Speaker","Wysp","Reclaimer","AA-126D","Noma","Elao","Eye of Gideron","Ghul","Djinni","Protocite","Photonic anomaly","Topheki b","Golden Vault","Plenara","Kashak","Caves of Siluri","Remembrance Rock","Iron Steeple","Horse Eye Orbital Plate","Dawnshore","Fireside","Shadeless Precinct","Lucent Shipyards","Chroma","Scintillatrix","Verdeon","House of the Void","Stellacuna","Corona","Atharaxum","Church of the Flamewalker","Disaj system","Nys system","Aristia system","Vanguard Craftworks","Gloaming","Assembly of Nations","Palace of Boundless Elevation","Shadow Veskarium","Thoqqua","Aratrosim","Dawnshore Spaceport","Janni","Suli","Rax","Salamander","Sylph","Ning","Ka","Spiral Basilica","Fury Place","Gravid Mound","Sephorian Archipelago","Last Laugh","Radiant Cathedral","Zeizerer Repository","Cumo","Abbey of Nevers","Malikah","Imp","Azer","Zeres","Efreeti","Tetrad","Mentrasi","Thyr","Nelentu","Syngnathrix","Bisporia","Havinak's Vortex","Qidel","Chainbreaker One","Nisis","Aylok","Izalguun","Duravor Kreel","Ironstar","Shadow Plane","Pharain","Ebethruya","Ship glitch gremlin","Eronesse","D'ziriak","Xovaikain","Automatrix","Far Portal","Deep Cultures Institute","Corona Artifact Divers","Tabori Cluster","Istamak","Kadrical","Shodrav","Surnoch","Mementor","Plague ooze","Vespers hound","Kukanou-2b","Heretic","Oneirian","Nodethe","Shadow Absalom Station","Ixcaliad","Shadow giant","Kayal","Unified Conservatory","Kaikyton","Great Old One","Outer God","Book of Unwritten Truths","Hamisfore Theatorium","Swordlight Cathedral","Golem","Taru Seco","Mountain eel","Squox","Drift cuttle","Khefak","Apari","Comet wasp","Warpmoth","Asteroid louse","Moonflower","Scavenger slime","Bodysnatcher slime","Planetoid beetle","Possibility Timer","Asteray","Asanatown","Tritidair","Kellixtrian","Gate of Twelve Suns","Broken Rock","Plasma ooze","Prexian mutantspawn","Creeping shade","01","Ihonva","Kunoris Vex","Saaruq-5","Riven Shroud","Ratheren","Void hantu","Terminator","Observer","Patrol","Cenotaph","Reaper","Spectre","Kalsarsa","Orphys","Roselight","Niaq","Aroggus","Shadow mastiff","Malfane","Sexton","Svartalfar","Vampire voice","Strawberry Machine Cake","Tarinth","King Xeros","Peak of Evening","Crate fiend","Void ooze","Sansorgis","Rovagug","Reklan","Master of Stars","Kax","Velstrac","Cantor","Gloomwing","Mining robot","Tenebrous worm","Emotivore","Solar wisp","Shantak","Arniselle","Eulogy","Reliquary","Heicoron IV","Nejeor VI","Dirgesinger","Crypt Warden","Ja noi","Cerebric fungus","Siege robot","Assassin robot","Mi-go","Living apocalypse","Dark Tapestry","Shelyn","Zolan Ulivestra","Azlanti royal battle regalia","Anvil of Torag","Ring of sustenance","Starstone compass","Ring of resistance","Amulet of camouflage","Magitech","Formian myrmarch","Formian worker","Calecor","Mindreaper","Brilliance","Tekhoinos","Levaloch","Haeshi-shaa","Shipmind","Ghost","Ilee","Chromatic dragon","Vlaka","Trox","Quorlu","Pahtra","Cybernetic golem","Predator drone","Ambassador","Damai","Bolida","Glass serpent","Comanide","Hulsa","Carnivorous crystal","Tashtari","Boneshard","Catacomb","Comet (starship)","Nexus","Shieldcraft","Seedship","Group Defense Frigate","Keep Diabolical","Pod","Arkship","Laborer","Uplifted bear","Embri","Spell chip","Colour out of space","Anchorite","Secure data module","Control module","System","Security key","Shock grid","Wingbot","Countermeasure","Twinsoul","Necropede","Firewall","Nanotech golem","Regnant","Corvette","Vortex shark","Pluprex","Kanabo","Seeder","Dreadlancer","Drone (Azlanti starship)","Drone (shirren starship)","Stellar protozoa","Entropy slug","Battle robot","Power archon","Kaukariki","Skeletal undead","Interdictus","Sanctum","Dropship","Titan Hauler","Arch Energy Consortium","Crest-eater","Golden League","Skyfire Legion","Deh-nolo","Arcanamirium","Izalraan","Copaxi","Jedarat","Daegox 4","Yashu-Indiri","Daxus","Upwell","Hibb","Brakim","Lezosk","Neskinti","Tabrid Minor","Preahan","Sanjaval Spaceflight Systems","Augmented","Android Abolitionist Front","Unseen","Novaspawn","Frujai","Orikolai","Symbiend","Paralith","Orry","Jiang-shi","Skreesire","Gosclaw","Hallajin","Vindicator","Sumpter","Monitor","Jinsul","Harrier","Screedreep","Arquand","Xaarb","Skreeling","Marrowblight","Odheo","Shadari Confederacy","Thresher lord","Corrovox","Iztheptar","Outpost Zed","Void hag","Shathrava","Void palm","Vilderaro","Stellifera","Radiation drake","Aeon stone network","Sepres VI","Athaeum","Arshalin","Embroi","Cybernetic zombie","Sharpwing","Aeon Guard","Nakondis","Daimalko","Silselrik","Primoria","Shimrinsara","Urrakar","Barrow","Veolisk","Naxikriot","Ungarato","Sky fisher","Writher swarm","Rebuilt","Void zombie","Vracinea","Autonomous heavy assault vehicle","Jubsnuth","Occult zombie","Oblivion shade","Midios","Bryrvath","Hobgar","Eohi","Acrochor","Living hologram","Carrion dreg","Endiffian","Yaruk","Lore guardian","Pale Butcher Scout","Synapse worm","Aeon stone","Thermatrod","Madelon's Landing","Adjutant robot","Mucilaginous cloud","Ghoul","Whiskered renkroda","Hiveguard","Millennia","Vanserai","Dominion of the Black","Draelik","Zo","Akata","Gatecrasher","Gray","Shimreen","Pale stranger","Mohrg","Kurobozu","Baykok","Corpsefolk","Garaggakal","Woioko","Ghibrani","Skittermander","Atrocite","Alluvion","Kish","Maraquoi","Wrikreechee","Ilthisarian","Zo!","Hesper","Elemental","Anhamut","Driftdead","Assembly ooze","Rauzhant","Kyokor","Barachius","Reptoid","Nuar","Skydock","Cathedralship","Grave Casket","Besmaran whelp","Oma","Burning Nail","Bone trooper","Death's Curtain Necrofighter","Dhalochar","El","Sepres Prime","Seprevoi","Dragonkin","Ferran","Kalo","Bantrid","Verthani","Anacite","Elebrian","Citadel of the Black","Witchwyrd","Morlamaw","Ryphorian","Urog","Khizar","Swarm","Queensrock","Strix","Borai","Sentient robotic organism","Scyphozoan","Striving","Selamid","Trillidiem","Qabarat","Astrazoan","Salvation's End","Arkeost","Elytrio","Tasch","House Arabani","House Cyrocaust","House Xicton","House Zeizerer","Naiaj","Torag","Quest for Sky","Nightarch","Asteroid","Vermin","Undead","Plant","Ooze","Monstrous humanoid","Fey","Dragon","Law","Chaos","Chaotic evil","Neutral evil","Chaotic neutral","Lawful evil","Neutral","Lawful neutral","Chaotic good","Lawful good","Alignment","Pact Council","Euphonics","Vercite ether-ballad","Eyebite rock","Shumka","Communication","Absalom Reckoning","Aroden","Starstone","291 AG","269 AG","240 AG","223 AG","154 AG","83 AG","Stardust Plague","Silent War","Battle of Aledra","67 AG","41 AG","36 AG","12 AG","Interstellar travel","Aleksana Guryari","7 AG","Animal","Outsider","Demon","Drift engine","Burning Archipelago","3 AG","0 AG","Ysoki (language)","Vesk (language)","Vercite (language)","Triaxian (language)","Shirren (language)","Kasatha (language)","Eoxian (language)","Castrovelian (language)","Brethedan (language)","Aklo","Akitonian (language)","Common (language)","Neutral (alignment axis)","Evil","Good","Arvin","Venture-captain","Arkanen","Osoro","Nchak","Melos","Hallas","Dykon","Marata","Kalo-Mahoi","Thyst","Chamari","Knights of Golarion","Ulmarid","Unbounded Wayfarer","Varos","Scoured Stars Incident","Godshield","Scoured Stars","316 AG","Material Plane","Soul","River of Souls","Mortality","Philosophy","Song of Silence","Singularitism","Sangpotshi","Prophecies of Kalistrade","Cycle","Green Faith","Magic","Spell","Vision","Yog-Sothoth","Shub-Niggurath","Azathoth","Lissala","Lamashtu","Groetus","Asmodeus","Arshea","Angradd","Kumara Melacruz","287 AG","Moon","Elindrae","Lashunta city-states","Radaszam","Pre-Gap","After Gap","Pact Standard Time","New Thespera","Preluria","Crimson Crew","Absalom Pact","Drake (starship)","Pegasus (starship)","Darkside","Shanavan","Drow","Orc","Gnome","Elf","Half-elf","Half-orc","Acquisitives","Blackwind Engineering","Sepulcher","Venture","Celita","Formian","Jadnura","Besmara","Ibra","Hacking","Root access","Module","Computer","Biotechnology","Augmentation","Necrograft","Tactical sniper rifle","D-suit","Weapon","Language","Construct","Tactical semi-auto pistol","Static arc rifle","Pirate","Dual ion laser pistol","Diode laser pistol","Magical beast","Aberration","Junklaser","Goblinoid","AbadarCorp","Damoritosh","Contemplative","Halfling","Ringworks Industries","Wanderer","Estuar","Ikeshti","Shobhad","Lorespire Complex","Guidance","Sarcesian","Security robot","Orocoran","Bloodbrother","Desna","Nyarlathotep","Urgathoa","Zon-Kuthon","Yaraesa","Weydan","Talavet","Oras","Eloritu","The Devourer","Sarenrae","Lao Shu Po","Iomedae","Haan","Ellicoth","Necrovite","Ksarik","Kasath","Humanoid","Necroglider","Tyrant","Omenbringer","Mauler","Immortal (starship)","ATech","Infosphere","Credit","Universal polymer base","Hylax","Year of Scoured Stars","Cult of the Devourer","Corpse Fleet","Hellknights","Sun","Aucturn","Apostae","Triaxus","Diaspora","Free Captains","Goblin","Azlanti Star Empire","Aspis Consortium","Deity","Corporation","Pharasma","Vesk Prime","Spaceship","Idari","Drift beacon","Shirren","Veskarium","Vesk","Starfinder Society Roleplaying Guild","Dwarf","Drone","Abadar","Triune","Xenowardens","Barathu","Stewards","Verces","Liavara","Bretheda","Aballon","Pact Worlds","Pact Worlds system","Starfinder Society","Castrovel","Lashunta","Eox","Akiton","Solar system","Starfinder","Absalom Station","Gap","Drift","Golarion","Mystic","Kasatha","Ysoki","Android","Human"
				);
		
		if(!run.isStarfinder()) return;
		for(var p:pages) {
			if(!run.getWiki().pageExists(p)) {
				run.getWiki().undelete(p, "Undelete accidental deletion");
			}
		}
		
		/*for(var p:run.getWiki().getPagesTranscluding("Template:Tlx")) {
			var txt = run.getWiki().getPageText(p.getTitle());
			var nTxt = txt.replaceAll("(?i)\\{\\{ *tlx *\\|", "{{tl|");
			if(!txt.equals(nTxt)) {
				run.getWiki().edit(p.getTitle(), nTxt, "Replace tlx with tl template");
			}
		}
		/*
		for(var p:run.getWiki().getPagesTranscluding("Template:Infobox/Book")) {
			if(p.getTitle().contains(":")) continue;
			var txt = run.getWiki().getPageText(p.getTitle());
			var nTxt = txt.replace("Infobox/Book", "Infobox|Book");
			if(!txt.equals(nTxt)) {
				run.getWiki().edit(p.getTitle(), nTxt, "Change style of calling semantic infobox");
			}
		}*/

		/*if(run.isStarfinder())
			return;*/
		/*var pages = run.getWiki().getPagesInNamespace("Property");
		for(var p:pages) {
			var text = run.getWiki().getPageText(p.getTitle());
			if(text.contains("{{Property")) continue;
			var newText = text.replaceAll(
					"\\* *\\[\\[(.*?)::(.*?)\\]\\]",
					"|$1=$2"
				).replaceFirst("^\\|", "{{Property\n|")
				.replaceAll("\\[\\[Category:", "}}\n[[Category:");
			if(!newText.equals(text)) {
				run.getWiki().edit(p.getTitle(), newText, "Use new Property template");
			}
			else {
				log.warn("No luck on {}", p.getTitle());
			}
		}*/
		
		/*
		var todo = Maps.newHashMap(
			"Positive Energy Plane", "Creation's Forge",
			"Positive Energy Plane/Inhabitants", "Creation's Forge/Inhabitants",
			"Positive Energy Plane/Locations", "Creation's Forge/Locations",
			"Positive Energy Plane/Nations", "Creation's Forge/Nations",
			"Positive Energy Plane/Settlements", "Creation's Forge/Settlements",
			"Negative Energy Plane", "Void",
			"Negative Energy Plane/Inhabitants", "Void/Inhabitants",
			"Negative Energy Plane/Locations", "Void/Locations",
			"Negative Energy Plane/Settlements", "Void/Settlements",
			"Shadow Plane", "Netherworld",
			"Shadow Plane/Inhabitants", "Netherworld/Inhabitants",
			"Shadow Plane/Locations", "Netherworld/Locations",
			"Shadow Plane/Nations", "Netherworld/Nations",
			"Shadow Plane/Organizations", "Netherworld/Organizations",
			"Shadow Plane/Settlements", "Netherworld/Settlements",
			"Material Plane", "Universe"
		);

		//rename categories
		for(var e : todo.entrySet()) {
			for(var p:run.getWiki().getPagesInCategory("Category:"+e.getKey())) {
				var text = run.getWiki().getPageText(p.getTitle());
				var newText = text.replaceAll("\\[\\[Category: *"+e.getKey()+" *((\\|[^\\]]*)?\\]\\])", "[[Category:"+e.getValue()+"$1");
				if(!newText.equals(text)) {
					run.getWiki().edit(p.getTitle(), newText, "Renamed category "+e.getKey()+" to "+e.getValue());
				}
				else {
					log.warn("No luck on {}", p.getTitle());
				}
			}
		}
		for(var e : todo.entrySet()) {
			if(run.getWiki().pageExists("Category:"+e.getKey()))
				run.getWiki().rename("Category:"+e.getKey(), "Category:"+e.getValue(), false, "Renamed category "+e.getKey()+" to "+e.getValue());
		}
		
		//search and replace
		for(var e : todo.entrySet()) {
			for(var p:run.getWiki().getPagesInCategory("Category:"+e.getKey())) {
				if(!p.getTitle().startsWith("File:")) continue;
				
				var text = run.getWiki().getPageText(p.getTitle());
				var newText = text.replaceAll("(\\| *keyword\\d+ *= *)(half-elves)(\\s*[\\|\n])", "$1aiuvarins$3")
						          .replaceAll("(\\| *keyword\\d+ *= *)(half-orcs)(\\s*[\\|\n])", "$1dromaars$3");
				if(!newText.equals(text)) {
					run.getWiki().edit(p.getTitle(), newText, "Renamed category "+e.getKey()+" to "+e.getValue());
				}
				else {
					log.warn("No luck on {}", p.getTitle());
				}
			}
		}*/
	}

	private void removeOldCiteBooks() {
		var subPages = run.getWiki().getAllSubPages("Template", "Cite book");
		for(var p:subPages) {
			if(run.getWiki().getPagesTranscluding(p).isEmpty()) {
				run.getWiki().delete(p, "No longer needed, superseded by Cite template");
			}
		}
	}

	private void replaceCitations() {
		var pages = run.getWiki().getPagesTranscluding("Template:Cite book");
		for(var p:pages) {
			if(p.getTitle().contains(":") && !p.getTitle().startsWith("Talk:")) continue;
			String title = p.getTitle();
			replaceCitations(title);
		}
	}

	
	private void replaceCitations(String title) {
		var txt = run.getWiki().getPageText(title);
		var nTxt = replaceNamed(txt);
		nTxt = replaceSimpleCases(nTxt);
		
		if(!nTxt.equals(txt)) {
			run.getWiki().edit(
				title,
				nTxt,
				"Replace references"
			);
		}
	}

	private String replaceSimpleCases(String txt) {
		var nTxt = replace(txt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\|? *\\}\\} *</ref>");
		nTxt = replace(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l1>\\d+) *\\}\\} *</ref>");
		nTxt = replace(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l1>inside back cover) *\\}\\} *</ref>");
		nTxt = replace(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<showas>(?<l1>\\\\d+)'?'?ff\\.?'?'?)\\}\\} *</ref>");
		nTxt = replace(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l1>\\d+) *\\| *(?<showas>\\d+'?'?ff\\.?'?'?)\\}\\} *</ref>");
		nTxt = replace(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l1>\\d+)(\\-|&ndash;)(?<l2>\\d+) *\\}\\} *</ref>");
		nTxt = replace(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(\\d+) *\\| *(?<l1>\\d+)(\\-|&ndash;|\\–)(?<l2>\\d+) *\\}\\} *</ref>");
		nTxt = replaceInsideCover(nTxt, "(?i)<ref *> *\\{\\{ *Cite book/(?<template>[^\\|\\}]+?) *\\| *(?<l>inside +(front|back) +cover|rear +inside +cover|inside +cover|front +inside +cover|ifc) *\\}\\} *</ref>");
		nTxt = replaceFollowing(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l1>\\d+)'?'?f'?'?\\}\\} *</ref>");
		nTxt = replaceFollowing2(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l1>\\d+) *\\| *(?<l2>\\d+)'?'?f'?'?\\}\\} *</ref>");
		nTxt = replaceMultiples(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(\\d+) *\\| *(?<l>\\d+((,|and) *\\d+)+)\\}\\} *</ref>");
		nTxt = replaceMultiples(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l>\\d+((,|and) *\\d+)+)\\}\\} *</ref>");
		nTxt = replaceNote(nTxt, "<ref *> *(?<cite> *\\{\\{ *[Cc]ite book/[^\\}]+\\}\\}) *(?<note>[^<]+?) *</ref>");
		return nTxt;
	}


	private static Pattern ID_REF = Pattern.compile("<ref +name *= *(\"(?<id>[^\"]+)\"|(?<id2>[^ >]+?)) *(/>|> *(?<value>\\{\\{[Cc]ite book/.*?) *</ref>)");
	private String replaceNamed(String txt) {
		Map<String, String> refs = new HashMap<>();
		ID_REF.matcher(txt).results().forEach(mr-> {
			var id = Optional.ofNullable(mr.group("id")).orElse(mr.group("id2")).trim();
			var value = Optional.ofNullable(mr.group("value")).orElse("").trim();
			refs.merge(id, value, (a,b)->a.length()>b.length()?a:b);
		});
		refs.entrySet().removeIf(e->e.getValue().isEmpty());
		
		return ID_REF.matcher(txt).replaceAll(mr-> {
			var id = Optional.ofNullable(mr.group("id")).orElse(mr.group("id2")).trim();
			if(!refs.containsKey(id))
				return mr.group();
			
			var clean = "<ref>"+refs.get(id)+"</ref>";
			var replaced = replaceSimpleCases(clean);
			if(!replaced.equals(clean)) {
				return replaced;
			}
			else return mr.group();
		});
	}

	private String replaceInsideCover(String txt, String pattern) {
		var m = Pattern.compile(pattern).matcher(txt);
		return m.replaceAll(mr -> {
			var template = mr.group("template");
			var l = mr.group("l").trim().replaceAll(" +", " ").toLowerCase();
			l = switch(l) {
				case "inside cover", "ifc", "front inside cover"->"inside front cover"; 
				case "rear inside cover"->"inside back cover";
				case "inside back cover", "inside front cover" -> l;
				default -> throw new IllegalStateException("Unhandled inside '"+l+"'");
			};
			var clean = "<ref>{{Cite book/"+template+"}}</ref>";
			var replaced = replaceSimpleCases(clean);
			if(!replaced.equals(clean)) {
				return replaced.replace("}}", "|"+l+"}}");
			}
			else return mr.group();
		});
	}
	
	private String replaceFollowing(String txt, String pattern) {
		var m = Pattern.compile(pattern).matcher(txt);
		return m.replaceAll(mr -> {
			var template = mr.group("template");
			var l1 = mr.group("l1").trim();
			var clean = "<ref>{{Cite book/"+template+"|"+l1+"-"+Integer.toString(Integer.parseInt(l1)+1)+"}}</ref>";
			var replaced = replaceSimpleCases(clean);
			if(!replaced.equals(clean)) {
				return replaced;
			}
			else return mr.group();
		});
	}
	
	private String replaceFollowing2(String txt, String pattern) {
		var m = Pattern.compile(pattern).matcher(txt);
		return m.replaceAll(mr -> {
			var template = mr.group("template");
			var l1 = mr.group("l1").trim();
			var l2 = mr.group("l2").trim();
			if(!l1.equals(l2))
				return mr.group();
			var clean = "<ref>{{Cite book/"+template+"|"+l1+"-"+Integer.toString(Integer.parseInt(l1)+1)+"}}</ref>";
			var replaced = replaceSimpleCases(clean);
			if(!replaced.equals(clean)) {
				return replaced;
			}
			else return mr.group();
		});
	}
	
	private String replaceMultiples(String txt, String pattern) {
		var m = Pattern.compile(pattern).matcher(txt);
		return m.replaceAll(mr -> {
			var template = mr.group("template");
			var l = Arrays.stream(mr.group("l").trim().split("\\D+")).map(Integer::parseInt).toList();
			var clean = "<ref>{{Cite book/"+template+"}}</ref>";
			var replaced = replaceSimpleCases(clean);
			if(!replaced.equals(clean)) {
				return l.stream()
					.map(v->replaced.replace("}}", "|"+v+"}}"))
					.collect(Collectors.joining());
			}
			else return mr.group();
		});
	}
	
	private String replaceNote(String txt, String pattern) {
		var m = Pattern.compile(pattern).matcher(txt);
		return m.replaceAll(mr -> {
			var cite = mr.group("cite").trim();
			var note = mr.group("note").trim();
			var clean = "<ref>"+cite+"</ref>";
			var replaced = replaceSimpleCases(clean);
			if(!replaced.equals(clean)) {
				note = StringUtils.removeStart(note, ".").trim();
				return replaced.replace("}}", "|note="+note+"}}");
			}
			else return mr.group();
		});
	}

	private String replace(String txt, String pattern) {
		var m = Pattern.compile(pattern).matcher(txt);
		return m.replaceAll(mr -> {
			var template = mr.group("template");
			var l1 = mr.namedGroups().containsKey("l1")?mr.group("l1"):null;
			var l2 = mr.namedGroups().containsKey("l2")?mr.group("l2"):null;
			var showAs = mr.namedGroups().containsKey("showas")?mr.group("showas"):null;
			
			var tTemplate = run.cache("resolve", template, this::resolve);
			if(tTemplate == null) {
				log.error("Can't resolve template '{}'", template);
				return m.group();
			}
			
			var sb = new StringBuilder("{{Ref|").append(tTemplate);
			if(l1!=null) sb.append("|").append(l1);
			if(l2!=null) sb.append("|").append(l2);
			if(showAs!=null) sb.append("|show as=").append(showAs);
			sb.append("}}");
			return sb.toString();
		});
	}

	private String resolve(String template) {
		if(run.cache("existing", "Template:Cite/"+template, run.getWiki()::pageExists)) {
			return template;
		}
		//test after resolving redirect
		template = StringUtils.removeStart(run.cache("redirect", "Template:Cite book/"+template, run.getWiki()::resolveRedirects), "Template:Cite book/");
		if(run.cache("existing", "Template:Cite/"+template, run.getWiki()::pageExists)) {
			return template;
		}
		
		//get real name from cite template
		var txt = run.getWiki().getPageText("Template:Cite book/"+template);
		var m=Pattern.compile("\\| *title *= *\\[\\[ *([^\\|\\]]+)").matcher(txt);
		if(m.find()) {
			var realName = m.group(1);
			realName = run.cache("redirect", realName, run.getWiki()::resolveRedirects);
			if(run.cache("existing", "Template:Cite/"+realName, run.getWiki()::pageExists)) {
				return realName;
			}
		}
		
		return null;
	}

	private void replaceWrongCitations() {
		for(var wanted : run.getWiki().getWantedTemplates()) {
			if(!wanted.startsWith("Template:Cite/")) continue;
			
			var template = wanted.substring(14);
			var txt = run.getWiki().getPageText("Template:Cite book/"+template);
			var m=Pattern.compile("\\| *title *= *\\[\\[ *([^\\|\\]]+)")
				.matcher(txt);
			if(m.find()) {
				var realName = m.group(1);
				realName = run.getWiki().resolveRedirects(realName);
				if(realName.equals(template))
					continue;
				
				var pages = run.getWiki().getPagesTranscluding(wanted);
				for(var p:pages) {
					var articleTxt = run.getWiki().getPageText(p.getTitle());
					var nTxt = articleTxt
						.replaceAll(Pattern.quote("{{Ref|"+template)+"(\\||\\})", "{{Ref|"+realName+"$1");
					
					if(!nTxt.equals(articleTxt)) {
						run.getWiki().edit(
							p.getTitle(),
							nTxt,
							"Replace references with correct name.");
					}
					else {
						log.error("What?");
					}
				}
			}
			else {
				log.error("No title in {} txt:\n{}", wanted, txt);
			}
		}
	}
	
	

}
