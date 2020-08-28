(ns exercises.chapter2.exercise2_5
  (:use exercises.math))

; Exercise 2.5:
; Show that we can represent pairs of nonnegative integers using only numbers
;   and arithmetic operations if we represent the pair a and b as the integer
;   that is the product 2^a3^b .
; Give the corresponding definitions of the procedures cons, car, and cdr.

(defn cons [a b]
  (* (expt 2 a) (expt 3 b)))

(defn extract-pair [pair divisor]
  (loop [n pair count 0]
    (if (not= (rem n divisor) 0)
      count
      (recur (/ n divisor) (inc count)))))

(defn car [pair]
  (extract-pair pair 2))

(defn cdr [pair]
  (extract-pair pair 3))

(cons 2 3)
; => 108

(car (cons 2 3))
; => 2

(cdr (cons 2 3))
; => 3

;(car (cons 2 (cons 5 7)))
;integer overflow

;(cons 5 7)
; => 69984

;(expt 3 69984)
;integer overflow

(defn cons [a b]
  (* (expt 2N a) (expt 3N b)))

(defn extract-pair [pair divisor]
  (loop [n pair count 0]
    (if (not= (rem n divisor) 0)
      count
      (recur (/ n divisor) (inc count)))))

(defn car [pair]
  (extract-pair pair 2N))

(defn cdr [pair]
  (extract-pair pair 3N))

(car (cons 2N (cons 5N 7N)))
;=> 2

(cdr (cons 2N (cons 5N 7N)))
;=> 69984
;very slowly

(expt 3N 69984N)
;=> 7143158730869697345973332848124938257050470450455257401923794847891527384649120343694015752623568329718832445818415699662447937466277731614048343734968284636354555383816946739773042353244621581153831021962091112556014345185758455585769686990216068316904736210835162211051044431996241156788018359984931531411493610086187056342399673630094148386138116447652565844507930493062903661504708271947781919415934390895176898538975103771195442788063660082562273261796755305403643424479964147940214195805055595528291686894309994681686478898783389429061416766458245601079019525234637292902932967658131778771844391186949013886002835010072753461256180826402392661771889726944129160781976756845513720773411945798471631986412945841681436860066964132155584296852116075786025002430587122362799917086297209236566417857420051971248209032665463051806989399293864984141269208855190948034206444833177048667530749428274905557850039368605132883132746325647059644982745240607707967134809173754563670323140368305988913032068314036376111263448852812714270466050112507549430843097465234292105205460476932817062024911679906469601061334574383721201767364712653953262437554953816399947581347188675321685479442828005301396205904910546427284963786862328588060946339262830540748176920400846054246425439557364606385385491734497438320479225443014891759164948208954718258473092621616832107574148028985619920373305624991605549589997764062380818188823160541377757121622912160830369437615673640848851837382625565478504806945246654019370752521874568554094359985751459247211243320068662775932789844108413445308468384418279151267017033397745380814254304385494388497793229440618460192842781701050813078497516255126054805638762690414941488587399286529522476401280881458520860629911462975723145646638679861977944098589596798656017541136098524999615257323689944049406160850474921842106523818285950731226799617268055602090897812742574726730848096866509612091442035631498370855241753620491994205241545149386254148309950845514343247972779309932927149408188848073678307541944076741481899910358247024287383296620518096859731463667539058167768100544702239997332609080119723213835667756964517969822684675029186051278970216809221680575850213570190729254783135185680341880327632777524902978840950635752800780913663648041402547800807266235813497073289098266931637308207641488343262520996417984640274909053331251921979656218938687037122201558762119628616681345813299200355480728961450387154279167965141532849744318086677391194611178121196885774155863278874402950768737833207268322340477028075629661708014983612803879040088707161210892502414749981296691658913102478994476698064326712542363126637077747032118998019028334598920317200777119090263791240033241241207615818348190915618536742826112823300405631737174699599135384942921437293551205423117750591743653395464139605518745674681751308782901650638492180268743736092348979938487684264441848458496552761186059513319917181303803122238566144189056901140294364598646030728920972191225550357986562959589720746052567186168173440158489030211927438019106365608371467303421608133733669757594831314312232659414179983013116555892141301423976259898827803837013789873672267065972705790832271238328056106439134873125027595795830362271845493787483621391250717943945098649203183892571817742699092415383704011140986562540598912507200150885597260113876954834757991683092039464844524884300086225145825619258807752380751817558603258941219137012341983262470140578211320030531135300055533181381630568564863904063667102696172468559484589621819125385055342098713977366638484998670950624147352232528861164393812295254633256255393263275586357600982169176818765545550741476851285303850939125571586078176106237059549408388016427812271493172020128131123567216050287824508913557643326194181640307810616164974116951482248518633275633328902958348892246878583758780259551593007257180013671602976958826473012270296736302502240108926039575979685275311583314146122648055322952587592853201415845068963448111402810007970182006694704310852774707440649256495921298168985361846159089685107730236761562879107073080602481717643705309042982486104995034554007013993976092277902814972812493827506643907833334782051082516135174451175067509316787100816551258610641351822776306126875383431657964682857346888986325659463302941689240379883807080589461016731232265782643410365833173947436707596908576142621366637754414201035082593527149952181982777120074112384866406506275838490892807886255375605918583375652559878878202074971676544403064403940534737698765664309578887456896594727308449931916984204939634964807499078691442697541718215990641858188832256113206426545602468176437612492020004927355794345797383561784472565945879757818748687088677723076157780655693119771112754994461571624675020464720497446581259962345759582808100981829091670238898913663307231939056656280117758797006464247843826520386390362579582346689670104247041890948399764787680604559172951005997423957570329550181246784126212519533166592606597902434854732969029022898888828493567438891843083878080043750557872930956172035439278374377606826905510700253386671072142848627437872221927882699732125549846643375120064298929177627884683499335481193470511166053871215439802464435673101467960955866535317160944998951351979177228538621561315254284805417953505598423063987794658360756891699565837801342773123638522015586805984176507025944135117435493958913873470983291943121060520882333764844001092124105879682559423344052215090238089891782228612639960050931423013784676371310429061127297310274386745801200009230994632717625175577505626745811520260350358884989988328234191157758849509228112521703128653011337358040975978009141254584970297931857644718459231029294079958286689933613891751170190949341582140566777723817342052656553228220593993291184370539369682859764024378730190627740100023377285653648789942948948071800786141732571482222326742309044656392136195354522922870428886932114334146447733261985342281574732110514437546933467814331180646664754731659254440222759140540584280579129998717552523196704432120281090390470162877138508658912760155414445053279450298757314101905695256259541867098021099779257115047571923765799177436436916551558644383492190693733066392494272068737439992707072955606823312317913433996545864222233783155195645941144559268294765528918026198797069797283777021393433649383985513896238419667970156416999440495851188562732555149714144217667373004069610776024910997007014790134494757651972517844846553319801676072641060068648034741250486868874695266817542952273796371196212492757799668561348234151957325924304246353211834013680973595201876424486362485631712985199184347348384667350840739553464603092709732304077636753356371060258553038243411477796359322172883725637678408015480733072808326451360616241342758989680385804611923370422099583123077274253769440550497090890644527678657800098889966239867800118249586803135138402324192013066702967086654605661117639093947973609904250470814630212501873436408582517140023795690833977275762790234962088632421622849467113869948268971917333995450507754009424646869207346324896401594113060744156422959298694013391386289514578041830831146596539615242361199119025849061808744368879607370096040407036488081629738378573931136889919118706741763863574538152923999399815783344474312428354996877825454678022814084507068115403051482259349984846989637292247072250539181488401578888573039787868392613526577293781958107019737816351699443285956915764848939138458055228444832289752325631980821739830267717466474748841924258894559004495561472349805950185397490163405731060985820045200358149398938239245780956301015303228647737411634966321348581008849197516938445212539975208628807466205796358548290745256182634381404350921384143631281396517557413468825618551977208154956009016212314587156158688464271709952953972294227073538634645939797939578729813596806105164970311251390984016777886224586785682677689943862810193169965666601826757976983862961495471209941193105078461882551245871688839394970604612127853707390316852755080347651656351210869459344279269416156089544161133048092745523212849269997518252639213012501719780052270229929117341452861012472510084721157433836998271206479284848886144314010115534729285548914416261034665775721073830447419383782204483503678731188882299613533591847473887749362636787836352491862096018690023365703133513327224815792939734055373137544911087592854368666851748697348435314646463881838585549784337082388814861352673825346556644978123269997218630288676574930696451208766800931381324841835687492766515709588374446665848211982097947496103442083029159800959548756482526961642470438713530803138256298815709922787869867580564115378296406956843635264846820099881531025785908579285661972337301599102292698546032807786092251739355233012759087383533508134855022159089098522157545447060975296611819796915669633367177873703508610287424565252489383674067019710862983314074639920044673300178520144675301494855389271648477291478823166023234377579871306546654429545575745872862529731977801148860294443167236598705491428616350998462396890309520679936071576035577092194516825256078791816362225836133680606435807391264235795590768809719479689524046313454455370372708117516164718709027958266385238074589093028933425230805883027950379862481764431116402786542898324523430659286305344148330125454640972863607206568194278579371015707532978155829960630728091803506178312330703062484574600569242813001220741366092190391286311830480266108572105240532365328067786277225109688773740973536926573405953057269189138905925802049245330446997524788591400375090758074888488380401730843749075838730372352206535619182213264782553336421071218743027551537532573977680110105196956723256980666714618799004015976282266256695129748326666769184591461163239648450462196089291690850681628495688443005131559916691002915927753117176626101300564120959008885653869147792636718501941900687268261783073659626760948810399623948074680218928201135304028051742735472111413182289688063089661642685242788501818891429244590428026844678286463728555962444395104156308726572157911558531963014242035458419224863399471576519225301026065148454756860079806784430315482708833680094150563433372224094147866127285492380020511319998456051062373377924374300611490084862992633612069075478042435271247968384241221145723058988654648609431457826881944884276914480277464793784451276190421921346808075512281434530225516771192637118899899717678471222781357897708031171527163131330471555378532714380894216160742175622863148505893983750097123847610839117148462134055544460850586429832243170869339456743742417849286741604752295225113111049555750568817350126175826623008466387687799863172584885201301200242467915774514835149495518845798109688572497178874993244714632512162317213093647298282228465312948388619152030607921172602360032858737204752882681780507290444045023879810018305112025070098498323698948393507207413917271145422793625941938762498590081707448542033353540962323317094132051232952059094729666725799368639813592832284914094747612251971078680635473961268939969299437795196007975651004654986478575151553226333998603599217365906051698488447985018912022971494133280931349945064441461144438397511082740401569364621884728472112153337428500656357211811826342194088725236060871388515370959478110585709813735202292647786700152614898506660702466395254315868178902535714112492246601382490697381320724731958130415191191942217289578348555462566667655797355900252376644388900984394860862072103910362887471657355019396880505357158681017412151047972073500847567764418929619806630619949795757620706242276252462358458370807326205386337662090559892236362979420344497275258376775513995194004241812342336676763343612917537620303119432493188867381344403875843291650575884836185181040625641315410557904373316619177652309865638464674150948800093704503303604821898543361326759438112974410580264056885027726477837143310107517761066827886160860937393377623003423411491707610501532319863636746359539388099465028606428988392957549318928577315165785747880287800552504043745585497188364228006148407452808284937876017744839059398613964724570974452622800952003791631637643307495989287486435465035230029743387075658924934711158283659399635337828900522726696818542549214327967034205386078948678625872913056487721319085714456340611565317338992716438316053740204502324047690081631262683859402018697704473873154201050145470264338471770797799828069204958724051152022743312999310789482326817551866535412305358706778620157404862115225829131878816889660369075286461707988685862314187394572531248814891641023386535078450118223571335177994914812359975486115542261303902641445596502506771154271648114268781217222732825340074859331359619863864172222414826404926063998627920453731307135179499561196111647942708687435799887673304524931923771264182935547284086389544899505028585401520802100715691693834230741648017695122993884206954254142371195672043791256572739216909648046842597149636834041839375948063243319265867386686561933174691364760650299675463453533930114393354367162954607958122128962155166210277996102501724039028806465529396285817704994653618228300612491183292525449547725219183967170808129408068299803990072550624899639814970590371112545557399469389800372091851816803827777979212237931341906408201341727396289117069020521904138827774647955473836990653524071157266580876498636928182755302969954286952270355214459596236551603317135029492225292292326125248393581422965370448555122244279635844186715253873295219628770343440706758660753283048408678484016911440087338876033976663165558173072321712973324733623272505015714840726590417111380279462558265096651351723766404283284934853840563607522671181855853980773381162413002017453707378364921571111109453514823187352115678487709074358006748470764744465433087419452934175105900773348681442831272728111560113106321096506265742472771408966535578463722975895960680231591309617771749585056127937029008082189862682612869545983002884376535369480339517877122315723218192641792118235029504997151637271889592138085196174442473747816466781081830144580416083965391292443974819172444956258636007429191803235522287587170375886222557913254920343390741883772442878216736342350954356702287972935534321561955772638661261755304824291120312526835351450889665264574235216119781450667766492174079015254289235689717529558566863144173562509086559604392542844816312370037175721345349944707265567340123409228558807321215725601691873871128736687863896338378611090855626796343735999120105447393842224299958477069131481018589382444721105846068967330459495945171325059950728775110259284244224672820436344993795921680258748765662951346760924054557911528085375018527428224573374997297506345341606280890520413736349136413643513304689321712563026720101367529195483951478617751951455241706950287646426906440216087174576301344187213483804933648427458533492273230534931229001496124155467014309293747546443198318903205009044338059169979441342385963690334501908184397916259614202024403257018807890343524048123064145039820800382096733829240963943721427361106445383054884906372045364227784171990237981350998057246161648124539902793691706263917169967742874051798702448644083601884959825426835396879827895052186452238317990717850745358057470121115807892263512978840653452642967467860448920833844537242183678150309159773260371165491298708722752444982964252730185970215561622033390125289078338786608281009339230460733596149101380180053658443783739300210668109808718410009208879275886668014862413374959774616168229629776493566401701369944682163866116322308194869965874817305170176912981283899789692131477415755540845599264360210354935231535742794333078889379326805978508577351491181467975350422832240580682725993914153721815853994465397807760619814857447039760228499106700877445199905613335200693078452961538273254529705632061854265351339104158554422962602026541970361715433729456358266227896516702440174738189339094915981685575949993974351237106515570879246292418960305204427201064488464953251249894730439433983898250931797071883827665791040513421383719101795660770213852316014236282948873060692472296064158471038352360156430551944578770726225173934597364753321160934644514748950692141920143392939253185794101850876916105445548249849459871256997998440100376185814183960081186368849898431489330430183554248886717986146727170568692150821355468754877928620328295461614455535766612092663230871425188300568451967813679200884898628306237968832769999800488388940831102244595235494348494121488047364111625184078597257997635266830233935352570338751740185882755125782540007459459631456072827068878378213232105439638813818745846499175672053880189925232369805760983568756027704447624632527250236805063798493646575841359308326263541562692215302342061663661507763206912467071395689158175227121392316365180234872762583313493053367387781647741005579109976239052771212963319249210270883175442729629647532749289205413028525057285790285220004342748866443348107346386494257275495677705905804045102650102314670240374067749660653291533596604183027657252162778042059610156806503507845716489206092158309381819414586919422455347833837277822566010986418130727643030011066075177286962550807913681772165538499273023787162438247800727550943332599614066971812054440789877189541759925421331500820050543012032370736950169272590610199125477942682244097334126204188278818533610441250572812646960358235900661037594277888074973267374086512587508682906361590599609326355032814928926048835884614539149540459594374423874457530636025092165549940424374956477900224506908836654137207551550958721940185111405957954317155142648828105843480486025515219276955572067214411314688820899844703494389192295765547598835362672831912541623185589139529947600264370925032831097817709633981022894420144947585842798792936243685702724379146850472237202600557553803507684844236329247629290153326881176053112352908328970749901502146989212035573464837167441437124655792871720608635789317579212425621715718657057136622361132134896533724547591232004749478290784591568781004649163474492920073173631334851712781739372936254874264418226294938385606343943309007597695547485216062679737563730822021585909209817828128753232579940048502465745827108590325919213519072775134837123481041980828053809536434204686253050102740922498136641483477398705960135364746505632852419772710267889928647939063086506375965815666930015199259127785581147332351759860644847163032955814719540698314406035285727731782165420773614306608946378015245499266638843705438262443551377634940048704671326033595125781999635214701429319537177100375277515252762727830533292840003107916225826097162023306106092272552724473639755167498404130020638905784874508315613540390997968159780667797441997445571700556417708642624546086457937439935070565364949590113288169360187648445309919299993029376976206480247115769777097248596711530928181997563930077226012139954031204068515918603299859454400615393539168232206424673187734174791600027647604684258350398098493672507598031864272443264491671853035519091228965573765373436843836749593048273708105093592633043493492917491864083313863474196483010251426403047961351136073470018196487585650270952932333976796913234372127405721356311384102677593114041874126537350511271077176843713264070645783632448282939785592740142855681318362125329352670652752016995384048926214306376870990154003767057190819399799368029192488731288348306108820892289012692422433230882088349493944108511732280876902375495649982994718448422616115804111589357650206523167437511361706106546419360573398218377978340860225287803656117532104526320548115174777592579677690421154952699991455249718285877241283060818907665526015617296142636478957993725346755811069962978364159998708917426853093352400088744811893548238225764668159243539970583024459110316345386792748987319280797313421422779816115717320138675777048029898435384710719694420732257909412623632208428958514570013025022826252356555478430514868773524664502522856277428903196424919200350264362777084593709831003725457745856113958535127192673465899261666493583665248714992090428107076011627104226137575255038770891785752023818705131133719185674052230164284132078793592156602558294059525198911890760909943429317918613276490215350798517562349825673984813652198863674786418943620333105711645320884775595199442325707763745821151485954418127380435321792448783786281906318213026581555893560882349837638921075545342061052964175799540002550461331127748287885201411790989449313267149965999179375161914968471368568339120932681593665859403940876176594691046975069841471994965223663499874867150165806423721932944169455873901454593049120468135143923919528398200767739199930164399104108833222119198605533245480668525760897131647596312240036686529201341486503003926685069792378125538610157436557738702061976527383550167934327389899832306049938313943632512934166557287474184068449821263997115155041304659897086377042001753755594393439542674129719829920048285983372799321852198685490959497664440082583439927764845117122426380600954279848294032744299503200055714093115379592121694506121007883509111945849999700068750977278369955382566598952948862787073195762573993746952911267010529690337231450239685731119293707394886197823829166187051686016282738819029809237199487495692329818002294952610880938881917173793226317837695648491200875031721569829690858641036994835836032173043178751746669274025431964999992146612089452512626370172674905719020253863439993893555414785085343601090923393441192001968851838421446058107987136462096796305557067245892124954932578694559111473339802408799377804072776396457541599493698005722773830032463912439309255082216257360198006780520174050608986341522010184603958044468722338209989303575468534183156765145624636871509158985535172273439459267666665435348220910047473055781819676804681138368165111977432380299294060915843308811436446312369939946991472339176162328157250969951072044006738017301458899799102611922375129736527960124623494171598103786508230792387129207680192750840346361998980143320726434824406225594277092190951688571413821688566456438068316214621448298081983011043987748717678651217930950683648780310847725975924777483504003961343860278391380836079461048337421100791527931586102744319573741953477118210604635373939711144157070408214158395214419663346763817204698998582731763391795614286296761324823038997222876441868392807927258062717870039788620690075021106158523020325185148527141309309331983693933450600735270858095607067310163878796929685908505537507285849710346569407803518824544127211390232096826862551478730435000765166668180385686625282244477176257421202383935220878955284073154292470625125258453770473626439486891730586856610572028539999806266320024740139323745226414796361144909768565139109348851549408629216318449087544948017850403857551473277232280849480219231186241782690043206695024445589185964685839969028888709700300881220662827420404569356702022230104577207948295338294234257478847993044049117926245231863897665034058627956432149172026552468807504877667676096331490835504539907742878552720417424747815612073842408615041583993263731424684323915126638345071989029574963350902625051676416433681297938109944249772073779400765431947779216982301518306086071219351296110014445787765517715997690358131803636465211841735692865516037331796470693236235077957063115993887711228947336168875995273229552200283670329977561335429351125705724045097969862116553542512542702566317690710848892843333795659058092727620910575659356058932106098045017527840090675106296243486462843088202468571306414444946649256227950080918824141966843986989363368352268289248163761251921999481070581432594076626482385392790815604191483665211254932544799562568108331495569136270332718745554705612369846557359729282184255527573328637000142347945553909102296280431880616618921550469327773736018498629750190014057463293540445554276951479374318180242622959973932863658422519615143639724551646087635263584748175280985356047035093060236711127189686574071716411868890544022803611254463407828548726532951901004397589230110681456659699254815363339119282065390432137860453125573379036643115032402264928212135951593530278773716162634001188850097119197256761975208760827797066604208682011577741276664603246807096162084297679915094339312324143478623273025535318730539565404061553176474565598228931403802380875137876426690319324376414284594434160114756927923237702180511820199458263666397256596387711660400194318745289915650460031891894689040010515929216491914579535559923737458894487998359399969302172455010108562387384517533503200057385239253437247701522842410036688344376923345218675016149913615489260035099956368553939317457195080487513209577985812621228967478899100065975941472742760703288117155548801495546789360918993845873081716893247464179606786008819797766965908899543929937991949909747272474533931289365015366854711765173387910727544789016503899697486534558739446223111241846864184302300023092449215897340859924149865574557995218573770856613571420314382704876019171405995149171751041567877079101736181572382011351407951939947342247184875133235824664651823355921002673897032891390063716965082947947867328257483339167714740511460743235570283294582854596691066209389352251051377975942754671455522404544924589736408864380072390082846008873184518424775525793140992275231687916441411200674523315028759779885821116940564976363857819117792251442195876091833960849364112518765580301434586820084242035138096668941054511508813673636343282961351545427418907966121837019237995324287013345606954683012697286535366857885733903829854450724952489570702453930510770707621755895898616715452402550835748318609757643585582095300868778209264369312437490560505474960152048650918173857725319993636893188727920641001561185867017272092107589910989188588010905466346475562531458385660583174317559703078208279470756910345184051299276434197741781496943960653354281644716747844123379812012150108051385908758457979940555114229899272131767782671428668156160255726349005472458075246850000617399389969261585153547413763439872802128595827772418565038893714505014252654295249001845288986383755750868074373460425522488641684004431408837248204126717924813379835332102669184000553627027924832981966724754272089094083969192432641740450560828401526400982667108279582147712777165766134392544961775955160773429680565223461770664811791297451448504049816068482360098477423575085086294217049404454039608963006758824373912187379431833598014092017769930214315488012948402509566909607083211663275212554151925037549945817028591963803448898178810606756841947843365424156048151608897633595243298224531983123756065673959078348276067938670519892575518782753951796753374210788576963471358712607863737082882348369031856129357186134424218193257041571199388300753457786971356124255283268920478352674074663031585829864012235821136963437209674166546272393638685249026268194611699383986204433530863287031768319591708234315773296727280910051016946178059403851119187144216580909793503270961898291755110535098370226857196031725077619555802985268265029733848910133508746970894837613696081686835688184299197587035540774341454497712636581313176900935664325765174259298003073741241600789659932209813953672240364554900158287324841236166941314958020508449861370754637300937191668913951756501817346251477349016369673446475709063508374636932722068486666291892661763014865206223038737858760044113175089422177332891465464021342143812008191378377200590922413448217109936864603126920722115878544565835029071210794653751309801749747348069199954864470188088539823276102680224423231483140268826728434364614033603784951052192400981395179582411053340464707827245546424499028102058644650586518607126538815012358984385018248892739548024600618369834530875923980140236125150684311201612568940675530248373393476874806628134964342900797153523703646758171976871044463914224988958449059625142608564870818207741640575826861024453518724724883352298404557111130924460742882235669009022756582994761789957184280892899680613347168188132598194773164181653190424062968564428911946774319137495273055794905966034336156585006596475502286791815969526069672898721281153335053544411259442282572188368142833208057849420698758722045612608035906874446163563404105438703746064975886841552859373362514837262299473187608160314608413777997276530684477516184075854498702461471868460083952835827099590142389593503173501449662801362237156560599025659959516134720325572867151056731131778121295364430093400996955966305533179302033634048745261541329989394753574311324003563319395964775912269189483868288627552418440014775083578733526537740179102634303861109012948440552055882445992944853779578519265835216575995334290498624321437795376909366546371624591721758407791559479606406810641043135217035435882049461443464973541240957125748336425107880243871203351344190570217181041356294511517836351931976368376056121779936841605992899232974779330844178615704827250905163817543484645013069558005782446588822848324866160606025660116000695303364785301743408926180346588948474401453557975599450816116240441598649673912196771214538221525894602733449994872074304082517109762724750261791733782806078103646611555532201828937430022415428051240070260614713291555406090748870035247039396299126366517962871495315348061042527973231593880029538458423466163652497870633759243923238371819907301036359341167372998139934091851228132921305521713321368562973100676215443378477775070381538697744562710971322003410972969752874905571035950492857252388103519916258975029812633842037062142715467789221074940936599115390696884036506639208348737628048396428541800385291834046969345778469544887671971327742350615075020465297676120458768176928300031779520620548098334395180680202532015029251733692174950387595771532113752413854063213125069086587845472707496482525613621739923799622249560800247390876779109886820297933322461612825633400592615981959734931508252418483529656211340107485482593541366883583630253692700335186834930557621410426011018463190713564172667489708759427853534502751999881169873513732863196298750608999199590889710567064468405440625663934492518182274576776661837747759533325510992119469829420532245982232290238138519931973184465627055403754415935541194210058619790397632820172744089080881097722604647088187357022842107491223020316925674804463474638304510936607172820855025699932567142137172467584599413121413720408288380921417500668166090534607464955400918771225685725557338870968629033547138727949099179859752627567711510327332671227901534669231434651571388530959547997036624260260321263875147311016656544022347808328569596788300925619646087283185896012866221358378622675638914899711899156746923954279416031440662277480378287540356824077196904005181380325981208220425025491175706133101993112467529227659811680731980374369745274159452809770129707826375569189126389765597427948512212566640224417577903529606704224580851531642918694166407872269755990173403087424807843760035353040492543861412309113438212123920075471291705712350742211915678820096560466816905335250442690444818853292635647940507240185799661975306506147036806895816982517130137520995675982104853862507673518412261071528034475556884497239868322793202520042342906295604185392680970185625932080976092836548601509992843748727911970337312713470418245767574869527982604287195896602676119657153484852426841032706357251909800528726989437489143799501340302112636032461935852110240053390262617562339268828462842719865995995929244947388791758257759566060904284278353992540126324862730209274428489848119539345668414396519709836941195772376483632761552653868826089515077735589747294049886968777506344800236011203064761120224176888722622647794919734156068840631465111980517395098277834824302555545184944244539291855681065711861059578848520169456703533940114729131948547648872487913585606273209582868682847850845922392313898055618182530612809164390265834307338820876641761285971578039399263110215295718375772548543127292113380194533177662031991613071645979249326328935046718535240621822288392569071165881875886229007596903802907774059465828306347919691896840021048997089235261168851965176197395977834057767750175377615888237019452653976076853813104965521652467751011683399442734841440785015043016074381039710287115541977949728457164165705562557001477529785442662339391057539712196370681606792784463055717317241706881349645603175687162739689807791215744983177801140212895181507600017656890597526002836784634857429952414038943501339647133173300811468222732860391384457280332886287306097442073662763703711239992439524574015175045392360189030536454290837521363779475813944519155769284365291255367554014503566140634901271282140096848603701338869721684338585844152283930126102592076537674550494456216148921940295626810203142259510968339458374610865372914392496103043547422626309140833965820325329110067474777418608284821794167769091841113413436249249729291225110307751392349723610964654648948555932938987778646406245296993813178349489462251826997262105502417101041592268433574277155748087623713714924947360610832440883470258782344061136744489770400234476904948787316868803622695968891041427005534461717488712727596937889993168907654781907834986851750244885719918698606509552708227888678423496713428964428402963220899843477325893078138837407660583121349601630045819610787840373700517026722170609736636444028713765567827086995520376303603661837997439348292250433097812293186024390168872639571066753909933329680656875175366598499974196315709910602959731583263317364478545224729268263046236515288296518347776143031522253341860088057945544690095944759733978316015657473751854483628555492469299462286209938748985715901755522529576342794453978603108184799131645961944818156023681N

(cdr (cdr (cons 2N (cons 5N 7N))))
;=> 7
;very slowly
