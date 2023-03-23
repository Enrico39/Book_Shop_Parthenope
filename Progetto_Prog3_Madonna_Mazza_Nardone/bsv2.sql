-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Feb 20, 2023 alle 13:26
-- Versione del server: 10.4.27-MariaDB
-- Versione PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bsv2`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `admin`
--

CREATE TABLE `admin` (
  `username` varchar(55) NOT NULL,
  `password` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Struttura della tabella `carrello`
--

CREATE TABLE `carrello` (
  `id_carr` int(11) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `TITOLO` varchar(255) NOT NULL,
  `AUTORE` varchar(255) NOT NULL,
  `PREZZO` float NOT NULL,
  `COPIE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `customer`
--

CREATE TABLE `customer` (
  `username` varchar(55) NOT NULL,
  `name` varchar(55) NOT NULL,
  `surname` varchar(55) NOT NULL,
  `email` varchar(55) NOT NULL,
  `password` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `customer`
--

INSERT INTO `customer` (`username`, `name`, `surname`, `email`, `password`) VALUES
('BennyNardone98', 'Beniamino', 'Nardone', 'BennyNardo@gmail.com', 'dragonball'),
('emety39', 'Enrico', 'Madonna', 'enrico.madonna@gmail.com', 'forzanapoli'),
('Enrico39', 'Enrico', 'Madonna', 'Emety@39', 'forzanapoli'),
('juarez', 'Gianni', 'Morandi', 'gianni@morandi.com', 'rosachemical'),
('LorenzoMazza98', 'Lorenzo', 'Mazza', 'lorenzo.mazza001@uniparthenope.it', 'forzanapoli'),
('Mati99', 'Matilda', 'Nardone', 'a@a', 'cocorito'),
('mcvita98', 'Maria Carmela', 'Vitale', 'mcvita@gmail.com', 'lorenzotiamo');

-- --------------------------------------------------------

--
-- Struttura della tabella `libro`
--

CREATE TABLE `libro` (
  `ID` int(4) NOT NULL,
  `TITOLO` varchar(50) NOT NULL,
  `AUTORE` varchar(20) NOT NULL,
  `ANNO_PUBBLICAZIONE` varchar(4) NOT NULL,
  `NUM_PAGINE` varchar(5) DEFAULT NULL,
  `QTA_DISP` int(255) NOT NULL,
  `CATEGORIA` varchar(256) NOT NULL,
  `SOTTOCATEGORIA` varchar(256) NOT NULL,
  `ISBN` varchar(18) NOT NULL,
  `PREZZO` float NOT NULL,
  `DESCRIZIONE` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `libro`
--

INSERT INTO `libro` (`ID`, `TITOLO`, `AUTORE`, `ANNO_PUBBLICAZIONE`, `NUM_PAGINE`, `QTA_DISP`, `CATEGORIA`, `SOTTOCATEGORIA`, `ISBN`, `PREZZO`, `DESCRIZIONE`) VALUES
(129, 'Tradimento. Thrawn', 'Lawrence Club', '2001', '234', 12, 'Fumetti', 'Fantasy', '9788828701439', 22.89, 'Thrawn dimostra la sua lealtà assoluta all\'Impero nel capitolo conclusivo della trilogia già bestseller negli Stati Uniti! L\'Imperatore ha messo da parte i progetti militari del Gran Ammiraglio in favore della Morte Nera del Direttore Krennic, e ora Thrawn dovrà farsi strada tra le gerarchie militari e al contempo guardarsi da una minaccia che viene da suo mondo natale'),
(130, 'Il vizio della curiosità', 'Philippe Daverio', '2020', '536', 23, 'Fumetti', 'Misteriosi', '9788891829979', 23.65, 'Per Philippe Daverio la curiosità non è un difetto ovviamente, anzi: è la base stessa della conoscenza. Aveva una grande voglia di capire, di indagare la complessità delle cose, e le sue qualità di divulgatore stanno soprattutto nel riuscire a trasmettere a tutti l\'entusiasmo per l\'arte, la storia, i musei, la cultura, e nella straordinaria capacità di parlare di qualunque argomento, anche dei più complessi, con semplicità e da prospettive spesso inedite. '),
(131, 'Spezzini per sempre', 'Alan Bassi', '2023', '170', 21, 'Letteratura', 'Antologie', '9788832213690', 13.39, 'Crocevia di partenze e ritorni, la provincia spezzina si fa in queste storie destino e salvezza, in cui la voglia di andare per mare e quella di restare coesistono. Un volume collettivo in cui amore e appartenenza si legano a doppio filo per questo angolo, meraviglioso, di territorio ligure, mostrando una forte identità di cui andare fieri.'),
(132, 'Freud. Con antologia freudiana', 'Sigmund Freud', '2023', '288', 8, 'Letteratura', 'Antologie', '9788833941776', 14.31, 'Partendo dal problema fondamentale che ne ha determinato tutto lo sviluppo, ovvero la terapia delle nevrosi, Cesare L. Musatti ricostruisce qui la genesi del pensiero del fondatore della psicoanalisi.'),
(134, 'Messer Porcellino', 'Richard Scarry', '2013', '46', 25, 'Letteratura', 'Classici', '9788804627746', 13.31, 'Al castello di Bellacittà è giorno di gran festa, ma all\'improvviso dal bosco spunta un enorme drago, ben deciso a rovinare il divertimento a tutti. Il re e la regina si rifugiano nel castello correndo a più non posso... ma c\'è un problema: manca la principessa Lily! '),
(135, 'Le due torri ', 'Richard Scarry', '1993', '393', 12, 'Letteratura', 'Classici', '2570180101197', 45.12, 'Le due torri\" è il tentativo più riuscito di trovare un titolo che comprenda i libri tre e quattro che sono così diversi; e può essere lasciato ambiguo — potrebbe riferirsi a Isengard e Barad-dûr, o a Minas Tirith e Barad-dûr; oppure a Isengard e Cirith Ungol'),
(136, 'The upper world', 'Femi Fadugba', '2022', '276', 76, 'Letteratura', 'Contemporanei', '9788804734789', 18.91, 'Oggi Esso, quindici anni, ha un dono: riesce a essere sempre nel posto sbagliato al momento sbagliato. E questa sua straordinaria capacità lo ha proiettato nel bel mezzo di uno scontro tra due gang rivali della periferia londinese. Tutto ciò che desidera è arrivare sano e salvo al weekend. Ma un colpo alla testa ricevuto durante un incidente lo fa accedere a un luogo al di fuori del tempo e dello spazio dove riesce a scorgere frammenti del suo passato e del suo futuro.'),
(137, 'Le guerriere che sfidarono l\'oscurità', 'Namina Forna', '2023', '336', 34, 'Letteratura', 'Contemporanei', '9788804756705', 19.86, 'Le dee sono vasti corpi eterei fatti di energia e luce di stelle, ognuno così grande da poter contenere un intero universo, eppure così piccolo da stare sul suo trono dorato. Sono illimitate, contraddittorie. E sono le mie madri. Sono passati ormai sei mesi da quando Deka ha liberato da un sonno secolare le antiche divinità di Otera, scoprendo al contempo la verità su di sé e il proprio scopo nel mondo. Ora nel regno imperversa la guerra, ma la battaglia è soltanto all\'inizio. Esiste infatti una forza oscura, sinistra e senza pietà, che minaccia l\'umanità intera e Deka e le sue sorelle di sangue devono fermarla a tutti i costi. Ma, a mano a mano che sviluppa nuove abilità, la ragazza è costretta a fare i conti con una verità scomoda: lei potrebbe essere al contempo la chiave per la salvezza di Otera ma anche la sua più grande minaccia. E questo non è l\'unico segreto che le dee le hanno tenuto nascosto…'),
(138, 'The diviners', 'Libba Bray', '2023', '636', 54, 'Letteratura', 'Contemporanei', '9788804756101', 20.99, 'Evie O\'Neill è stata cacciata dalla sua sonnacchiosa cittadina dell\'Ohio e catapultata tra le frenetiche strade di New York. Ed è as-so-lu-tis-si-ma-men-te felice! È il 1926 e New York è così eccitante, piena di bar clandestini, ragazze alla moda e borseggiatori. L\'unico inconveniente è che Evie deve vivere con lo zio Will e la sua malsana ossessione per l\'occulto. Ciò che teme è che lo zio scopra il suo segreto: un potere soprannaturale che finora le ha portato solo guai. Ma quando la polizia ritrova il cadavere di una ragazza marchiato con un simbolo misterioso e Will viene chiamato a indagare, Evie capisce che il suo dono potrebbe aiutare a catturare un serial killer. Inizia così una pericolosa danza con l\'assassino, mentre attorno a lei, nella città che non dorme mai, mille storie si dipanano.'),
(139, 'Spare', 'Prince Harry', '2023', '416', 71, 'Letteratura', 'Lingue Straniere', '9780857504791', 34.75, 'It was one of the most searing images of the twentieth century: two young boys, two princes, walking behind their mother\'s coffin as the world watched in sorrow - and horror. As Diana, Princess of Wales, was laid to rest, billions wondered what the princes must be thinking and feeling - and how their lives would play out from that point on. For Harry, this is that story at last. '),
(140, 'La vita intima ', 'Niccolò Ammaniti', '2023', '312', 41, 'Letteratura', 'Letterature', '9788806255152', 18.05, 'Maria Cristina Palma ha una vita all’apparenza perfetta, è bella, ricca, famosa, il mondo gira intorno a lei. Poi, un giorno, riceve sul cellulare un video che cambia tutto. Nel suo passato c’è un segreto con cui non ha fatto i conti. Come un moderno alienista Niccolo\' Ammaniti disseziona la mente di una donna, ne esplora le paure, le ossessioni, i desideri inconfessabili in un romanzo che unisce spericolata fantasia, realismo psicologico, senso del tragico e incanto del paradosso.'),
(141, 'Il mondo alla finestra', 'Emanuela Pulvirenti', '2022', '304', 3, 'Arte e Musica', 'Storia dell\'arte', '9788817164627', 18.05, 'Chi di noi non si è mai perso a guardare il mondo fuori da una finestra? A tutti è successo almeno una volta, anche a tantissimi artisti e pittori che hanno trasformato quelle visioni in meravigliosi dipinti e creazioni. Opere che per la prima volta vengono raccolte in questo libro da Emanuela Pulvirenti - architetto, esperta di illuminotecnica, insegnante di storia dell\'arte e fondatrice di Didatticarte, uno dei siti di divulgazione artistica più seguiti e amati in Italia. Dagli affreschi classici di Pompei ai maestri rinascimentali, dalle tele seicentesche al romanticismo, dalle avanguardie del \'900 all\'arte contemporanea, pagina dopo pagina prende forma un caleidoscopio di finestre silenziose e abitate, reali e immaginarie, intime e giocose, aperte verso un paesaggio o racchiuse su scene sacre o di vita quotidiana.'),
(142, 'Outsiders', 'Alfredo Accatino', '2017', '208', 34, 'Arte e Musica', 'Storia dell\'arte', '9788809857575', 27.55, 'Trentaquattro storie di uomini e donne che meritano un posto nell\'arte del Novecento. Quel posto che la vita gli ha negato. Se ogni immagine è la storia di una storia, le storie che queste immagini raccontano - e le vite che ci stanno dietro - hanno a volte dell\'incredibile, fanno scoprire che l\'arte è un dono ingombrante, che una biografia artistica non necessariamente ha un lieto fine, che artisti mediocri hanno lasciato tante opere trascurabili e un capolavoro, oppure che alcuni creatori di capolavori sono stati trascurati perché nati nel posto sbagliato, o perché catalogati pazzi, omosessuali, antisociali, ebrei, o perché erano talmente poveri o malati da scomparire all\'attenzione dei più. E queste storie da un\'altra storia dell\'arte, parallela e invisibile, ci insegnano a diffidare dalle categorie correnti - i maestri, gli epigoni, i minori... - e a scoprire, anche grazie alle illustrazioni, percorsi svolti con maestria e in abbondante anticipo sui tempi, a volte, o su binari che magari nessuno più avrebbe mai ripercorso. Piccoli racconti di vite difficili da ricostruire, per mancanza di fonti; schegge di creatività che lasciano sbalorditi; emozioni personali travasate in forme e colori che riescono qui a farsi finalmente condividere.'),
(143, 'Lettering creativo e calligrafia moderna', 'Andrea Balconi', '2019', '128', 7, 'Arte e Musica', 'Calligrafia', '9788809857575', 11.41, 'Un libro interattivo, ricco di idee creative, esercizi divertenti e progetti facili da realizzare con istruzioni passo a passo. Dalla calligrafia alle insegne luminose, tutte le tecniche per trasformare la scrittura in un\'opera d\'arte con l\'aiuto di matite, penne, gessetti, pittura.'),
(144, 'Scriptorium', 'Màlleus', '2017', '144', 73, 'Arte e Musica', 'Calligrafia', '9788804680550', 17.01, 'Con le sue sette semplici regole e i principi base della calligrafia, questo libro ti accompagna alla scoperta di un\'arte millenaria e del suo potere benefico, in grado di calmare la mente mettendoti in contatto con la tua dimensione spirituale.'),
(145, 'Notti di Tokyo', 'Mateusz Urbanowicz', '2022', '160', 2, 'Arte e Musica', 'Disegno', '9788867226900', 23.75, 'Il maestro degli anime giapponesi ci invita a una passeggiata notturna per le vie di una delle megalopoli più affollate del mondo. Urbanowicz ci racconta il suo arrivo nella capitale giapponese per lavorare in uno studio di animazione, rievocando quei momenti di solitudine provati quando gli sono venuti a mancare i punti di riferimento più familiari. In quel momento, l\'unica via di fuga è stata per lui quella di vagabondare di notte, in una sorta di meditazione che gli ha permesso di assaporare al meglio la città. Come nel suo primo libro, Botteghe di Tokyo, l\'autore richiama la nostra attenzione sui dettagli architettonici, ma anche sulle atmosfere che ha percepito e sulle tecniche adottate per ottenere una stupefacente maestria della linea e del colore. Le vedute presenti in Notti di Tokyo, un po\' come le stampe di Hokusai e Hiroshige, sedurranno gli amanti della cultura e dell\'illustrazione giapponese, sia per la qualità pittorica sia per l\'approccio pedagogico con cui Mateusz definisce il suo lavoro nell\'ultimo capitolo. Inclusa nel volume, l\'intervista dell\'autore con uno dei più grandi registi d\'animazione giapponesi saprà deliziare gli appassionati del genere.'),
(147, 'La figura nella moda', 'Tiziana Paci', '2006', '304', 25, 'Arte e Musica', 'Fashion Design', '9788889628027', 27.55, 'Giunto alla sesta edizione il libro è stato completamente rinnovato in quantità (304 pagine invece di 160), ma soprattutto in qualità. Più ricco di disegni a colori, si presenta come un volume unico nel suo genere che focalizza la sua attenzione sul rigore rappresentativo e la chiarezza dei disegni. Unico per la semplicità del testo, è diventato, a pochi anni dalla sua uscita, un classico e un best-seller internazionale, già tradotto in sette lingue. Disegnato da stiliste professioniste, è un sostegno necessario e prezioso e un valido punto di riferimento per coloro che già esercitano l\'affascinante professione di stilista di moda o di fashion designer, ma soprattutto per chi a tali professioni intende accostarsi. Un manuale, dunque, e uno strumento indispensabile per una comunicazione efficace e specifica che rientri nei gusti e nei parametri del fashion designer.'),
(148, 'Fashion e textile design', 'Maria Grazia Soldati', '2009', '158', 97, 'Arte e Musica', 'Fashion Design', '9788883913006', 17.19, 'È difficile oggi classificare e dare una definizione univoca di cosa è moda. A livello progettuale, il sistema prodotto-moda racchiude in sé stimoli di energia vitale in grado di determinare una nuova relazione con se stessi e con il mondo esterno; la motivazione che porta il consumatore a un investimento in \"prodotti moda\" deve essere straordinaria, non tanto per il contenuto quanto per l\'esperienza. Gli autori mettono a fuoco il valore progettuale del textile e del fashion design, settori economicamente strategici per il made in Italy. Pensato per studenti ed esperti del settore, nella prima parte il testo mette in evidenza l\'evoluzione della ricerca tessile, la progettazione e la realizzazione del tessuto tramite il telaio, l\'innovazione tecnologica apportata dall\'ingegneria tessile. Nella seconda parte la ricerca tratta l\'evoluzione della storia della moda di cui sono evidenziati i momenti progettuali più rilevanti diventati oggi delle \"permanenze\" di stile nel tempo. Il testo è completato da due saggi; il primo, tratta l\'importanza dell\'archivio storico aziendale come fondamentale \"luogo\" della ricerca scientifica; il secondo pone l\'accento su come sia importante il corpo, protagonista della progettazione nel settore della moda. Infine il caso studio, Stone Island, marchio 100% italiano, crogiuolo di conoscenze design driven.'),
(149, 'Così ho vissuto', 'Boris Pahor', '2013', '320', 2, 'Biografia', 'Etnica e Culturale', '9788845274176', 19.95, 'Il Novecento è il secolo di Boris Pahor: ne ha vissuto gli orrori e le conquiste, facendosene testimone per eccellenza. Il racconto della sua esperienza esistenziale è, dunque, un racconto etico e vivo, denso di avvenimenti e aneddoti che seguono un tracciato cronologico ma mai banale o scontato. La sua biografia si sviluppa attraverso questo racconto, fatto da Pahor in prima persona, e contestualizzato dalla curatrice. Non si tratta solo di una autobiografia ma anche di una storia di Trieste, storia in cui si specchia la storia del novecento europeo. Così, accanto alla storia viva, in presa diretta di Trieste, della comunità slovena e delle altre comunità che arricchivano e in parte arricchiscono, la città; accanto alla cronaca della disgregazione dell\'impero asburgico, della Grande guerra, dello squadrismo e del fascismo, Boris Pahor racconta la sua crisi esistenziale, l\'esperienza della guerra in Africa, l\'adesione al fronte di liberazione sloveno e la conseguente deportazione nei lager, il difficile ritorno alla libertà e alla vita.'),
(150, 'Anne Frank', 'Sid Jacobson', '2011', '149', 21, 'Biografia', 'Etnica e Culturale', '9788817047364', 21.95, 'È il 1918, e l\'Europa esce a fatica dall\'incubo della Prima guerra mondiale. L\'ebreo tedesco Otto Frank è tra i pochi \"fortunati\" a tornare sano e salvo al proprio Paese, una Germania terribilmente provata dall\'orrore appena trascorso e afflitta dal fardello dei pesanti risarcimenti di guerra. Ma la vita è fatta per continuare, l\'esistenza di milioni di individui riprende nel tentativo di annullare il ricordo della violenza: è così anche per il tenente Frank e per la moglie Edith, che nel 1 925 vivono il presente di una giovane coppia innamorata, la cui gioia potrà aumentare solo con la nascita di due splendide figlie. Nella solida famiglia Frank la dolce, pacata Margot e la più piccola e vivace Anne vivono dunque un\'infanzia serena, nonostante gli intrecci della storia mondiale e gli effetti della politica nazionale rendano ogni giorno più difficile la vita di tutti. Anne è un\'ignara bambina di tre anni quando il partito nazionalsocialista si fa seriamente strada nel Parlamento tedesco, e ne ha solo quattro quando Adolf Hitler viene nominato cancelliere. Non può ancora capire il nuovo incubo in cui lei e milioni di altri ebrei stanno per precipitare: l\'incubo di una vita negata, della dignità fatta a pezzi e calpestata, l\'orrore infinito della bestialità umana che giorno dopo giorno, ormai adolescente e costretta a nascondersi dai nazisti in un rifugio segreto, Anne descrive nel famoso diario che la renderà poi famosa come il più forte personaggio-simbolo della Shoah. '),
(151, 'Il mondo di ieri', 'Stefan Zweig', '2017', '392', 23, 'Biografia', 'Europea', '9788804683247', 13.78, 'Molto più che semplice autobiografia, «Il mondo di ieri» è il ritratto di un\'epoca scomparsa, la suprema epopea di quella \"Felix Austria\" che tanto segnò la storia e la cultura europea, quel mondo nel quale «ognuno sapeva quanto possedeva e quanto gli era dovuto, quel che era permesso e quel che era proibito: in cui tutto aveva una sua norma, un peso e una misura precisi». Al centro di tutto sta la Vienna imperiale, simbolo di un\'epoca indimenticabile che Zweig - esponente di una generazione che «ha imparato a fondo l\'arte preziosa di non rimpiangere il perduto» - descrive in tutto il suo splendore e in tutte le sue contraddizioni. Pubblicato postumo, «Il mondo di ieri» è segnato da un\'atmosfera autunnale che imprime all\'intera opera il severo suggello della modernità.'),
(152, 'Alessandro Schiavi', 'Carlo De Maria', '2008', '328', 11, 'Biografia', 'Europea', '9788849131086', 27.55, 'Alessandro Schiavi (Cesenatico 1872-Forlì 1965) studia con Antonio Labriola all\'Università di Roma, laureandosi in giurisprudenza nel 1895. Nella capitale si afferma come giornalista e studioso di scienze sociali. Entra nella prima redazione dell\'\"Avanti!\", dove rimane fino al 1903. Si trasferisce, quindi, a Milano, allora in piena crescita demografica e industriale, e i suoi interessi si definiscono lungo due filoni principali: l\'indagine sociale sulla condizione operaia e l\'edilizia popolare. È funzionario della Società Umanitaria, direttore dell\'Istituto per le case popolari e assessore comunale nelle prime giunte socialiste della città lombarda (1914-22). Sotto il fascismo non abdica all\'impegno intellettuale e sfida la censura del regime, collaborando come traduttore con Giovanni Laterza e Benedetto Croce. Nel secondo dopoguerra è tra i protagonisti della ricostruzione in Romagna, senatore socialdemocratico nella seconda legislatura repubblicana e fervente europeista. Prima ancora che per una federazione di Stati (di governi centrali), Schiavi lotta per una federazione di comunità locali, da realizzare attraverso il Consiglio dei Comuni d\'Europa, nato a Ginevra nel 1951.'),
(153, 'San Paolo apostolo', 'Henri D. Saffrey', '2010', '192', 11, 'Biografia', 'Storica', '9788821529290', 10.01, 'Per anni, accompagnando gruppi di pellegrini nelle località evangelizzate dall\'apostolo, l\'autore ha raccolto fonti storiche, bibliche, classiche, informazioni archeologiche, testimonianze coeve alla predicazione di Paolo. Nel libro ripercorre quegli itinerari, attraverso i villaggi, lungo le strade, nei porti; rievoca miti, usanze, episodi e li colloca nei templi e nelle piazze di Atene, Efeso, Corinto. Qui hanno origine le nuove comunità cristiane, di cui l\'autore ricostruisce gli entusiasmi e le debolezze. In questo contesto storico vengono ricostruiti la vita e il pensiero dell\'apostolo Paolo.'),
(154, 'A Milano e a Napoli', 'Sandro Landro', '2011', '160', 0, 'Biografia', 'Storica', '9788821529290', 17.11, 'Il volume, ripercorrendo alcuni aspetti della ricca e complessa produzione scientifica e giornalistica di Vincenzo Cuoco, ne ricostruisce la dimensione filosofica e politica mostrandone la centralità e originalità nella tradizione culturale italiana e il ruolo assunto all’interno della riflessione del primo risorgimento.'),
(155, 'Biografia di donne famose', 'Apollo Edizioni', '2020', '514', 11, 'Biografia', 'Personaggi Famosi e Leader', '9788831202527', 17.11, 'Tra mito, realtà e leggenda.'),
(157, 'Le guerre di Napoleone', 'Jacques Garnier', '2019', '334', 4, 'Biografia', 'Militare', '9788861026278', 11.41, 'In un mondo segnato dalla permanenza dei conflitti fin dalla più alta antichità, la storia ha perpetuato solo i nomi di pochissimi signori della guerra che furono talmente esperti nel loro campo da lasciare il segno nei vertici più alti di quella che è stata considerata una scienza, se non un\'arte. Lo stesso Napoleone ne individuò un certo numero: Alessandro, Annibale, Cesare, Gustavo-Adolfo, Turenne, il principe Eugenio di Savoia-Carignano, Federico II. Alcuni generali straordinari, stimati da Napoleone, hanno lasciato una formulazione più o meno completa, più o meno priva di artifici, della loro manière. Napoleone lo fece, ma in modo piuttosto parziale, nei suoi scritti di Sant\'Elena e in una certa misura nei Bulletins de la Grande Armée. E dunque necessario ritrovare il nucleo essenziale della sua scienza e della sua arte della guerra nello studio preciso degli esempi tratti dalle sue campagne militari e dalle battaglie. Lo scopo di questo studio non è tanto quello di stabilire una nuova storia delle guerre napoleoniche, ma piuttosto quello di far comprendere più da vicino, attraverso l\'esame delle sue principali campagne o battaglie, l\'evoluzione e il carattere della scienza militare di Napoleone.'),
(158, 'Pompeo. Una biografia militare', 'Nic Fields', '2013', '150', 6, 'Biografia', 'Militare', '9788861022317', 7.55, 'Gneo Pompeo Magno celebrò il suo trionfo con una lunga serie di successi sul campo, all\'età di 24 anni; a 35 aveva raggiunto i vertici di comando della Roma repubblicana, salutato come l\'Alessandro Magno romano. I suoi exploit contro i seguaci di Mario, poi le sue campagne nella penisola iberica, contro i pirati nel Mediterraneo, e ancora le sue grandi vittorie in Oriente contro Mitridate e altri avversari contribuirono a estendere in notevole misura le frontiere di Roma. Nessuno poteva vantare analoghi successi militari nella tarda età repubblicana, ma il suo posto nella storia è stato sancito da quanto scrive Cesare nei suoi \"Commentarii\": questo libro offre al lettore un ritratto imparziale e completo delle campagne belliche di Pompeo e delle sue qualità di comandante.'),
(160, 'Asterix e il grifone ', 'Zerocalcare', '2021', '48', 44, 'Fumetti', 'Comici', '9788828704447', 9.41, 'Un volume inedito di Asterix. Accompagnati da Panoramix, il più celebre druido di tutta la Gallia, Asterix e Obelix si preparano a partire per un lungo viaggio alla ricerca di una creatura misteriosa e terrificante. Ma cosa sarà?'),
(161, 'La storia della mia vita. Spider-Man', 'Chip Zdarsky', '2021', '48', 23, 'Fumetti', 'Marvel', '9788828706625', 4.75, 'A sorpresa, Chip Zdarsky e Mark Bagley aggiungono un capitolo alla loro straordinaria opera. I riflettori sono ora puntati su J. Jonah Jameson e il suo odio per Spider-Man nel corso degli anni. Ma decenni di ossessioni porteranno infine dei frutti o solo alla distruzione totale? Un volumetto speciale da affiancare a Spider-Man: La storia della mia vita.'),
(162, 'Tomie', 'Junji Ito', '2017', '740', 12, 'Fumetti', 'Misteriosi', '9788832750430', 17.01, 'Tomie è una donna capace di sedurre e corrompere la mente degli uomini, trascinandoli in una spirale di follia. Tomie non teme la morte, poiché nulla può ucciderla. Tomie torna alla vita per vendicarsi, per sempre, senza pietà. Preparatevi a sprofondare in un abisso di terrore.'),
(164, 'Avengers. Uniti!', 'Marvel', '2018', '96', 16, 'Fumetti', 'Marvel', '9788893290500', 11.49, 'Un libro elegantemente rilegato e illustrato a colori che racconta le storie degli Avengers, con un testo semplice, adatto anche alla lettura autonoma dei giovani lettori.\n'),
(166, 'Secret wars ', 'Jonathan Hickman', '2022', '312', 4, 'Fumetti', 'Marvel', '9788828724001', 19.01, 'Realtà dopo realtà, il multiverso si è sbriciolato, e ora rimangono solo due mondi: quello degli eroi come li conosciamo e la Terra dell’Universo Ultimate. E ora stanno per scontrarsi. \n'),
(167, 'Tre Joker', 'Geoff Johns', '2022', '160', 43, 'Fumetti', 'DC', '9788828737711', 18.05, 'La raccolta completa della miniserie evento di Geoff Johns e Jason Fabok. Batman ha scoperto che esistono ben tre Joker, e il loro piano è in atto da anni! Nemmeno l\'aiuto di Red Hood e Batgirl potrebbe bastare... Una storia che ha ridefinito per sempre il Principe della Risata.\n\n'),
(168, 'Il mondo. Batman ', 'DC comics', '2021', '184', 43, 'Fumetti', 'DC', '9788828735984', 11.49, 'Un\'antologia di racconti che vedono il Cavaliere Oscuro in trasferta, realizzati da quattordici prestigiosi team creativi! Per l\'Italia, il Pipistrello approderà a Roma per affrontare un avversario mai visto prima! Un volume di prestigio che contiene la primissima avventura di Batman ideata da autori italiani!\n\n'),
(169, 'Cuphead. 1', 'Zack Keller', '2021', '122', 13, 'Fumetti', 'Fantasy', '9788892970335', 12.26, 'Direttamente dall\'omonimo videogame, la prima graphic novel che ne espande l\'universo con i protagonisti Cuphead e Mugman e l\'inconfondibile stile che ricorda i cartoon anni \'30. Dopo aver fatto incetta di premi grazie al suo stile unico, Cuphead approderà nel 2021 in una serie animata su Netflix!\n\n\n'),
(170, 'Final Fantasy. Lost stranger. 2', 'Hazuki Minase', '2018', '192', 2, 'Fumetti', 'Fantasy', '9788832756029', 6.56, 'Shogo Sasaki e sua sorella minore Yuko lavorano per Square Enix. Un giorno vengono investiti da un camion e si ritrovano catapultati nel mondo di «Final Fantasy!» Per trarre in salvo una bambina, Yuko viene uccisa da un drago. Consapevole del fatto che, in questo mondo, l\'incantesimo di resurrezione Reiz non esiste, Shogo dà il via a una feroce battaglia per vendicare la sua amata sorellina.\n\n\n\n'),
(171, 'Rete padrona', 'Federico Rampini', '2014', '278', 23, 'Computer e Tech', 'Apple', '9788807070365', 17.11, '\"Mi trasferii a San Francisco nel 2000 per vivere nel cuore della Silicon Valley la prima rivoluzione di Internet. Ci ritorno oggi da New York e ho le vertigini, e un senso d\'inquietudine. La velocità del cambiamento digitale è stata superiore a quello che ci aspettavamo e ormai la Rete penetra in ogni angolo della nostra vita: il lavoro, il tempo libero, l\'organizzazione del dibattito politico e della protesta sociale, perfino le nostre relazioni sociali e i nostri affetti. Ma la Rete padrona ha gettato la maschera. La sua realtà quotidiana è molto diversa dalle visioni degli idealisti libertari che progettavano un nuovo mondo di sapere e opportunità alla portata di tutti. I nuovi Padroni dell\'Universo si chiamano Apple e Google, Facebook, Amazon e Twitter. Al loro fianco, la National Security Agency, il Grande Fratello dell\'era digitale. E poi i regimi autoritari, dalla Cina alla Russia, che hanno imparato a padroneggiare a loro volta le tecnologie e ormai manipolano la natura stessa di Internet. Con questo libro vi porto in viaggio con me nella Rete padrona. È un viaggio nel tempo, per confrontare le speranze e i progetti più generosi di un ventennio fa con le priorità reali che plasmano oggi il mondo delle tecnologie. È un viaggio tra i personaggi che hanno segnato quest\'epoca, da Bill Gates a Steve Jobs, a Mark Zuckerberg, e tra tanti altri profeti e visionari meno noti, che già stanno progettando le prossime fasi dell\'innovazione.\"\n\n\n\n'),
(172, 'Mac OS Sierra al 100%', 'Furio Piccinini', '2016', '270', 6, 'Computer e Tech', 'Apple', '9788820377298', 18.99, 'La nuova versione del sistema operativo di Apple offre numerose funzionalità che migliorano la sicurezza, il lavoro sui documenti, l\'ottimizzazione delle prestazioni e, finalmente, incorpora anche il tanto atteso Siri, l\'assistente digitale che già equipaggiava iPhone e iPad. La straordinaria flessibilità di macOS Sierra vi permetterà di svolgere qualunque compito in pochissimo tempo e con questo libo imparerete a: Installare, configurare e personalizzare macOS Sierra; Aggiornare il sistema operativo; Configurare e usare Siri per fare qualunque cosa; Configurare e usare iCloud per i vostri documenti; Proteggere e gestire i file, creando dei backup; Imparare tutto sulle Preferenze di Sistema del Mac; Creare e gestire nuovi utenti e attribuire i permessi; Migliorare l\'interazione del Mac con i social media; Scaricare e gestire le app tramite Mac App Store; Navigare in Internet e usare la posta elettronica; Imparare a usare il Terminale; Passare efficacemente da Windows a Mac; Usare i trucchi dei professionisti.\n\n\n\n'),
(173, 'Guida facile al disegno CAD 2D e 3D', 'Luigi Santapaga', '2020', '342', 61, 'Computer e Tech', 'CAD', '9788850335329', 23.92, 'AutoCAD è il software per la grafica CAD più conosciuto al mondo. Milioni di utenti lo impiegano per realizzare disegni tecnici in ogni settore della progettazione, dall\'ingegneria all\'architettura, dalla meccanica all\'impiantistica, dall\'elettronica alla cartografia. Questo manuale tutto a colori si rivolge a chi inizia a usare AutoCAD e accompagna il lettore passo passo nell\'acquisizione di un metodo di lavoro professionale e nell\'utilizzo dei più importanti strumenti per il disegno 2D e per la modellazione 3D. Nulla di essenziale viene tralasciato: dall\'esplorazione dell\'interfaccia ai formati e alle tecniche di disegno, passando attraverso gli strumenti per gestire oggetti, le librerie di blocchi, l\'inserimento di tabelle, la quotatura e la stampa dei disegni. Il testo è basato su AutoCAD versione 2021 per Windows, ma molte spiegazioni sono applicabili anche alle versioni precedenti, mentre diversi concetti possono essere adattati anche ad AutoCAD per i sistemi Apple. Ogni capitolo è corredato da esercizi che permettono di testare le conoscenze acquisite.\n\n\n\n\n'),
(174, 'CAD e Decreto «Semplificazioni»', 'Giuseppe Vitrani', '2021', '323', 15, 'Computer e Tech', 'CAD', '9788828829607', 18.05, 'Gli ultimi anni sono stati caratterizzati da un profluvio di interventi normativi, non sempre organici, volti a fornire le basi per lo sviluppo digitale della pubblica amministrazione e di larghi settori del mondo privato. Un ruolo centrale spetta senz\'altro al legislatore europeo che, con l\'emanazione del Regolamento n. 910 del 2014 (noto come eIDAS), ha introdotto negli ordinamenti dell\'Unione il concetto di servizio fiduciario e ha dettato una disciplina unitaria in particolar modo in materia di identificazione elettronica delle persone fisiche, di firme elettroniche e di servizi elettronici di recapito certificato. Il legislatore nazionale ha così potuto dare pieno impulso al Sistema Pubblico di Identificazione Digitale, ora riconosciuto in tutta l\'Unione Europea, e ha riformato più volte il Codice dell\'amministrazione digitale, da ultimo nell\'estate del 2020, sia per adeguarlo alla normativa europea sia per dettare norme che possano portare ad un pieno \"sviluppo digitale\" dello Stato; vanno in tale direzione le norme sul domicilio digitale e quelle sulle firme elettroniche. Il periodo immediatamente precedente la pubblicazione del presente volume ha visto la pubblicazione delle Linee Guida sulla formazione, gestione e conservazione dei documenti informatici, un testo fondamentale per tradurre in applicazioni pratiche i principi normativi. L\'opera si pone pertanto l\'obiettivo di fornire un quadro organico, e al tempo stesso pratico, dei suddetti interventi con l\'ottica di costituire un\'utile guida soprattutto per professionisti e imprese che si confrontano quotidianamente con l\'ecosistema digitale italiano ed europeo.\n\n\n\n\n\n'),
(175, 'Excel livello avanzato', 'Alberto Clerici', '2022', '224', 7, 'Computer e Tech', 'Certificazioni', '9788848322997', 17.96, 'Questo volume rappresenta un manuale autorevole e completo per prepararsi all’esame di certificazione ICDL Advanced Spreadsheets e risulta utile per chiunque voglia imparare l’uso di Excel 2010-2013-2016-2019-2021 e 365 a livello avanzato. Il testo copre fedelmente gli argomenti del Syllabus, dalle funzioni nidificate alle opzioni avanzate dei grafici, dagli strumenti di analisi con le tabelle pivot alle macro, ed è concepito come una vera e propria guida visuale: ogni argomento è spiegato nei dettagli e descritto passo passo, con l’ausilio di oltre quattrocento immagini che consentono di memorizzare e ripetere facilmente le operazioni descritte.\n\n\n\n\n\n'),
(176, 'Concetti di informatica e fondamenti di Python ', 'Cay S. Horstmann', '2019', '786', 2, 'Computer e Tech', 'Informatica', '9788891635433', 51.31, 'Questo volume è dedicato a Python, un linguaggio di programmazione diffuso da anni tra i professionisti grazie alla sua potenza e semplicità sintattica, e di utilizzo sempre più frequente anche in ambito universitario. Il testo guida il lettore all\'acquisizione degli strumenti concettuali classici della programmazione strutturata e introduce alla programmazione ad oggetti, caratteristica del linguaggio Python, presentando gli argomenti, oggetti, classi, ereditarietà, incapsulamento, polimorfismo, con chiarezza e completezza. Completano ed arricchiscono il volume casi svolti che permettono di elaborare strategie di problem solving, domande di auto-valutazione, esercizi di approfondimento teorico e problemi di programmazione. Il libro, ideale riferimento per un corso introduttivo di programmazione basato su Python, si rivolge agli studenti dei corsi di laurea in informatica e ingegneria e, per la sua particolare comprensibilità ed efficacia didattica, è anche un ottimo strumento di auto-istruzione.\n\n\n\n\n\n\n'),
(177, 'Alan Turing. La nascita della scienza informatica', 'White Star', '2017', '137', 40, 'Computer e Tech', 'Informatica', '9788854036123', 17.99, 'Alan Turing visse forse troppo poco. Nato a Londra nel 1912, si tolse la vita a Wimslow nel 1954 senza aver festeggiato il suo quarantaduesimo compleanno. Eppure ebbe il tempo di cambiare il mondo per sempre. Tutte le volte che accendiamo un computer, stiamo raccogliendo il frutto del suo lavoro. Più precisamente: tutte le colte che, acceso un computer, utilizziamo un programma o visitiamo un sito in rete, quello che lo schermo ci mostra si basa sulla macchina che porta il suo nome. Si tratta di uno strumento teorico di calcolo che manipola i dati contenuti su un nastro di lunghezza potenzialmente infinita, seguendo un insieme prefissato di regole ben definite: gli algoritmi. Quante colte ne abbiamo sentito parlare? Quando Turing mise a punto questo modello rivoluzionario aveva soltanto ventiquattro anni. Era il 1936. Oggi questo matematico britannico è giustamente considerato il padre della moderna informatica e dell\'intelligenza artificiale. Conoscerlo significa conoscere meglio quello che facciamo ogni giorno quando utilizziamo un telefono cellulare o impieghiamo un programma di videoscrittura.\n\n\n\n\n\n\n\n'),
(178, 'SQL e Excel', 'Marco Ferrero', '2016', '223', 33, 'Computer e Tech', 'Database', '9788850333745', 19.98, 'Questo testo si rivolge a chi lavora con grandi quantità di dati e ha a disposizione come strumenti il linguaggio SQL, per creare e utilizzare database, ed Excel, il software Microsoft per analizzare dati. Si tratta di tecnologie potenti e consolidate che utilizzate insieme permettono di ottenere risultati professionali senza il bisogno di ricorrere ad altre soluzioni complesse e costose. Il punto di partenza sono database relazionali come MySQL e SQL Server che il lettore è accompagnato a esplorare e quindi a utilizzare con il linguaggio SQL. I dati, una volta acquisiti, passano poi in Excel attraverso flussi di importazione dedicati, per essere raffinati in fogli di calcolo e visualizzati in forma grafica. L\'approccio didattico unisce teoria e pratica mediante numerosi esempi i cui file sono liberamente scaricabili online. Alla fine il lettore avrà capito come padroneggiare le logiche e le modalità per affrontare in modo efficace analisi dei dati tra database e fogli di calcolo.\n\n\n\n\n\n\n\n\n'),
(179, 'Basi di dati', 'Serena Sensini', '2021', '360', 197, 'Computer e Tech', 'Database', '9788850335534', 27.92, 'La progettazione e l\'interrogazione di database è un elemento centrale di applicazioni, servizi e piattaforme di vario tipo e dimensione e non può essere ignorata da chi lavora allo sviluppo e al mantenimento di architetture e soluzioni IT. Questo manuale fornisce i fondamenti per lavorare con i dati e i relativi sistemi di gestione, analizzando le tipologie esistenti e le modalità per archiviare ed estrarre informazioni. Dopo aver spiegato come funziona il linguaggio SQL, vengono affrontati i database relazionali (MySQL e PostgreSQL) e quindi quelli NoSQL (MongoDB, Elasticsearch). A questo punto si passa al delicato tema della progettazione di una nuova base di dati suggerendo le best practice per ottenere un buon risultato ed evidenziando quali errori evitare. Per concludere vengono introdotte le tecnologie per lavorare con i big data, le modalità per gestire l\'archiviazione su cloud e come ottenere la miglior qualità del dato. Con 185 esercizi disponibili online, una guida pratica, ricca di esempi e suggerimenti, pensata per studenti e professionisti che vogliono imparare a lavorare al meglio con i dati.\n\n\n\n\n\n\n\n\n'),
(180, 'Le basi della cucina asiatica', 'Jody Vassallo', '2010', '83', 3, 'Cucina', 'Asiatica', '9788896621097', 18.75, 'La cucina e i viaggi sono al centro della vita di Jody Vassallo. Globe trotter infaticabile, ha dedicato metà della sua vita a percorrere il mondo in lungo e in largo alla ricerca di nuovi sapori e ingredienti per elaborare ricette originali; l\'altra metà a trasmettere la sua passione per la cucina e a raccontare le storie che ha vissuto.\n\n\n\n\n\n\n\n\n\n'),
(181, 'School of wok', 'Jeremy Pang', '2022', '208', 1, 'Cucina', 'Asiatica', '9788867533633', 26.61, 'Questo libro celebra tutti i successi, grandi o piccoli, della famiglia di School of Wok passata, presente e futura. School of Wok è una piccola scuola di cucina asiatica fondata nell\'autunno dell\'ormai lontano 2009 da Jeremy Pang nell\'esclusivo quartiere londinese di Covent Garden, che negli anni è cresciuta a dismisura grazie alle centinaia di migliaia di allievi (e buongustai!) entusiasti che ne hanno saputo apprezzare e condividere la filosofia. E questo è il primo libro di School of Wok, con una raccolta di ricette che vi aiuteranno a intraprendere il vostro viaggio verso est direttamente dalle vostre cucine. Trasformate questo ambiente della casa nel luogo in cui avviene questa vera e propria magia e imparerete e riprodurre le squisitezze delle più grandi e blasonate tradizioni gastronomiche orientali: dalla Cina alla Thailandia, dal Vietnam a Singapore, dalla Malesia all\'Indonesia, passando per le Filippine, la Corea e, naturalmente, il Giappone. È qui che scoprirete tutti i segreti delle cotture (al vapore, stir-fry, frittura) con il wok e che creerete, da soli o in compagnia di familiari, amici, ma anche dei più piccoli, deliziosi piatti tipici delle culture asiatiche da gustare poi tutti insieme. Una volta finito il \"wokking and rolling\" (ossia il cucinare), sedersi a un tavolo per condividere cibo, vino e soprattutto allegria, apprezzando ogni singolo boccone preparato insieme, sarà infatti una vera e propria festa.\n\n\n\n\n\n\n\n\n\n\n'),
(182, 'Erbette che passione!', 'Miriana Caldart', '2015', '160', 1, 'Cucina', 'Cucina Calda', '9788866433576', 8.36, 'Un prezioso e pratico manuale per saper riconoscere e utilizzare le erbe di montagna. Oltre a presentare le schede botaniche con le caratteristiche di ogni pianta, questo libro illustra le loro proprietà officinali e l\'uso che se ne può fare in cucina attraverso sfiziose e appetitose ricette. Il tutto è accompagnato da immagini e interessanti curiosità. Si tratta quindi di una piccola enciclopedia che vi illustrerà tutto quello che c\'è da sapere sulle erbe che incontrerete durante le vostre passeggiate in montagna e come potrete utilizzarle.\n\n\n\n\n\n\n\n\n\n\n'),
(183, 'Cucinare draghi', 'Marco Caldarola', '2013', '96', 12, 'Cucina', 'Cucina Calda', '9788896876534', 6.99, 'Come tutti i libri dedicati alla gastronomia tipica, Cucinare draghi nasce dall’amore per il cibo e per il territorio, dalla conoscenza delle tradizioni e le leggende locali. Ma proprio nelle leggende il nostro autore ha notato un dettaglio spesso trascurato: l’esistenza dei Draghi, animali fantastici ricordati nelle cronache del nostro medioevo e descritti in un antico trattato culinario... Attraverso una serie di ricette curate nei minimi dettagli e basate sulla carne di diverse specie di Draghi, si propongono numerose soluzioni per uno sfarzoso banchetto rinascimentale: dalle minestre alle braci, dai pasticci ai dolci. Un modo originale per stimolare la fantasia del lettore ma non solo: i più intraprendenti troveranno, alla fine di ogni ricetta, suggerimenti per sostituire la carne di Drago con altri tipi di pietanze.\n\n\n\n\n\n\n\n\n\n\n\n'),
(184, 'La nuova bibbia del BBQ', 'Jamie Purviance', '2020', '352', 12, 'Cucina', 'BBQ', '9788827601242', 28.45, 'Un condensato di abilità per preparare grigliate su tutti i tipi di barbecue, a carbone, a gas o elettrici, gestire correttamente le temperature, adattare la cottura ai tipi di alimento e imparare l\'arte dell\'affumicatura... Spiegazioni passo passo per padroneggiare le tecniche e capire tutto in men che non si dica. Con 200 tra consigli utili, tecniche e trucchi e 750 fotografie di piatti, gesti tecnici e materiali. Un libro per i principianti ma anche per tutti gli appassionati di barbecue.\n\n\n\n\n\n\n\n\n\n\n\n\n'),
(185, 'I signori della brace', 'Michele Capano', '2018', '184', 3, 'Cucina', 'BBQ', '9788872836736', 18.91, 'I signori della brace vengono dai monti e sono veri e propri fanatici delle grigliate. Nei loro ravioli di agnello la carne diventa l\'involucro, le loro tortillas alpine vengono preparate con la farina di segale e i lombatelli (diaframma) vengono intrecciati, così da avere più spazio sulla griglia. Grazie a questo libro, la tua prossima bistecca avrà un successo strepitoso.\n\n\n\n\n\n\n\n\n\n\n\n\n\n'),
(186, 'Quando un piatto fa storia', 'Susan Jung', '2020', '448', 9, 'Cucina', 'Arti Culinarie', '9788867225255', 37.91, 'Quando un piatto fa storia celebra i piatti più iconici e autorevoli degli ultimi trecento anni. Dai primissimi ristoranti all\'innovativa scena gastronomica di oggi, ecco sfilare la Pesca Melba di Auguste Escoffier al Savoy e il Sushi di Jiro Ono al Sukiyabashi, il PurZ di patate di Joël Robuchon al Jamin, la Parte croccante delle lasagne di Massimo Bottura all\'Osteria Francescana e l\'Oliva sferica di Ferran e Albert Adrià a elBulli. A cura di un gruppo internazionale di esperti, questo diario globale di oltre 200 imperdibili creazioni culinarie narra una storia che ha definito il corso della gastronomia e tracciato la strada per l\'odierna cultura della ristorazione.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n'),
(187, 'Le altre Delizie tarantine', 'Silvia Quero', '2020', '160', 5, 'Cucina', 'Arti Culinarie', '9791280174017', 10.51, 'Il libro è suddiviso in \"Delizie\" (capitoli tematici), rinnovate con l\'introduzione di nuove categorie, quali le \"Delizie architettoniche\" e le \"Delizie teatrali\", con un occhio rivolto verso le tradizioni che tanto stanno a cuore ai tarantini e che tanto si fa per fermarle nel tempo e per tramandarle alle generazioni che verranno. Le Delizie del XXI secolo, che rendono omaggio alla grande azione divulgativa fatta tre secoli or sono dal D\'Aquino, rappresentano una raccolta breve di nozioni di varia natura sul passato e, talvolta, sul presente di Taranto, scritte in un linguaggio rapido, diretto (ben lontano dagli esametri latini...); un promemoria per quei tarantini che, magari, non ricordano mai abbastanza bene quanto sia bella Tarde Nuestre; un biglietto da visita per tutti coloro i quali, non tarantini, vogliano apprendere di più su questa singolare e contraddittoria città che, vista dal di fuori, appare grigia e fumosa ma che, una volta scoperta e svelata, si manifesta quale è: una perla baciata dal sole, in grado di incantare tutti coloro i quali abbiano avuto la fortuna di posarvi gli occhi.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n'),
(188, 'Un po\' più dolce', 'Damiano Carrara', '2020', '224', 33, 'Cucina', 'Dolci', '9788830901193', 17.58, 'Si parte dalla più semplici per poi avventurarsi alla scoperta di ricette ad alto tasso di creatività. Un libro che Damiano voleva scrivere da molto tempo, per far entrare tutti, finalmente, nel suo laboratorio. Ricette dai gusti più disparati e per ogni grado di preparazione. Infatti, come ogni viaggio che si rispetti, si tratta di un percorso che parte dalla conoscenza dei fondamentali. Un primo capitolo è dedicato alle ricette di base utili a preparare i dolci contenuti nel libro, ma anche per crearne di propri, per poi arrivare, nei capitoli successivi, a ricette davvero sorprendenti, dedicate ai pasticceri più coraggiosi. \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n'),
(189, 'Mamma, cucino da solo! ', 'Matilda Nardone', '2021', '64', 23, 'Cucina', 'Dolci', '9788857309569', 15.21, 'Questo libro-laboratorio consente al bambino, in linea con i principi del Metodo Montessori, di \"fare da solo\", di portare avanti la realizzazione di un dolce in completa autonomia, con l\'utilizzo di un comune vasetto di yogurt e un cucchiaio da cucina. In ogni ricetta viene presentata la foto dell\'ingrediente e accanto il numero di vasetti o cucchiai da riempire, il che richiama un\'altra attività Montessoriana che è quella del travaso, tanto amata dai piccoli. Nel libro sono inserite delle etichette da applicare sui contenitori da cucina che conterranno gli ingredienti, in modo che il bambino li associ chiaramente e in maniera univoca alle immagini che trova nel libro.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n'),
(190, 'L\' almanacco di Naval Ravikant', 'Eric Jorgenson', '2021', '229', 2, 'Educazione', 'Almanacchi', '9788836200542', 20.99, 'Diventare ricchi non è solo una questione di fortuna e la felicità non è un tratto della personalità che si possiede dalla nascita. A volte può sembrare che questi traguardi siano fuori dalla nostra portata, ma la ricchezza e la felicità sono semplicemente delle capacità che possiamo acquisire e allenare. Ma quali sono le abilità che ci possono aiutare a raggiungere lo scopo e come possiamo acquisirle? Quali sono i principi che dovrebbero guidare i nostri sforzi? Come possiamo renderci conto dei reali progressi che facciamo lungo questo cammino? Naval Ravikant è un imprenditore e investitore.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n'),
(191, 'Bestiario universale del professor Revillod', 'Miguel Murugarren', '2010', '40', 4, 'Educazione', 'Almanacchi', '9788857601021', 13.33, '«Attraversando terre e mari ignoti, risalendo vette sconosciute ed esplorando gli abissi piÃ¹ profondi, calcando strade ferrate e librandomi su palloni aerostatici, sempre ho seguito, inalterabile, una stella polare: la frase in esperanto che, orgogliosa, campeggia nell\'emblema dell\'Università di Bratislava, La Scienco, torco kaj grido da Homaro. Sì, la Scienza, fiaccola e guida dell\'Umano genere, faro di progresso e civilizzazione, è stata il messaggio che ho bramato diffondere in quelle terre laddove abbisognava la mia missione. Possa il lettore trarre beneficio da questo lascito che dispongo per i posteri.» (Professor Revillod). Il Bestiario Universale è un grande gioco: non solo perché invita a combinare 16 illustrazioni di animali comuni come la tigre, il rinoceronte, l\'elefante... e le loro descrizioni, ma anche perché propone come vere le divertenti (e spesso inventate di sana pianta) informazioni presenti nel testo, nelle illustrazioni, nell\'introduzione. Con un\'estetica che ricorda i manuali di zoologia sistematica del XIX secolo, questa serie di false incisioni realizzate in base alle annotazioni di naturalisti ed esploratori inserisce dettagli e nozioni che disorientano, e gioca con le convenzioni che fissano il limite tra realtà e finzione; il risultato è leggero e umoristico, ma soprattutto ludico!\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n'),
(192, 'Il grande atlante degli animali ', 'Mario Gomboli', '2015', '40', 43, 'Educazione', 'Atlanti e Mappe', '9788809817371', 9.89, 'Il modo migliore per imparare le specie animali della Terra? Un atlante cartonato pieghevole! La cover è lo spicchio di un grande mappamondo illustrato. In ogni continente sono localizzati tutti gli animali nel loro habitat naturale, così, il bambino potrà riconoscere a colpo d\'occhio le varie specie e nel contempo scoprire in quale zona della Terra vivono. Il libro è riccamente illustrato. Età di lettura: da 3 anni.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n'),
(193, 'Gigante 2023', 'Fabio Gigante', '2022', '800', 0, 'Educazione', 'Cataloghi', '9788889805442', 23.75, 'Il catalogo Gigante delle monete italiane consta di ben ottocento pagine a colori ed è editato, annualmente sin dal 1992, dall\'omonima azienda che opera nel mercato numismatico dal 1967. Il catalogo descrive e valuta, nei vari stati di conservazione, tutte le monete contemporanee italiane coniate in Italia ed all\'estero dall\'invasione napoleonica alla fine della lira (quindi euro escluso). Nel catalogo, sono state inoltre completate le monetazioni di quei sovrani che, al potere durante l\'era moderna, sono stati spodestati da Napoleone ma, restaurato il potere in seguito alla disfatta di quest\'ultimo, hanno ripreso a battere moneta in era contemporanea. Gli acquirenti del catalogo, grazie ad un codice univoco, possono sia accedere alla versione on-line del catalogo (www.catalogogigante.it) sia scaricare l\'apposita App per Android o iOs. La versione on-line, che sarà completata nel tempo, contiene, attualmente, le seguenti monetazioni: Regno di Sardegna (dal 1720), Regno d\'Italia, Colonia Eritrea, Somalia Italiana, Albania (secondo periodo), RSI, Gettone Loubet, Gettoni delle Fiere Esposizioni, Gettoni della CRI, Repubblica Italiana.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n');
INSERT INTO `libro` (`ID`, `TITOLO`, `AUTORE`, `ANNO_PUBBLICAZIONE`, `NUM_PAGINE`, `QTA_DISP`, `CATEGORIA`, `SOTTOCATEGORIA`, `ISBN`, `PREZZO`, `DESCRIZIONE`) VALUES
(194, 'Catalogo dei genitori', 'Claude Ponti', '2009', '46', 0, 'Educazione', 'Cataloghi', '9788883622007', 24.51, 'I tuoi genitori sono pesanti ? Sono stancanti, avari, appiccicosi, urticanti, barbosi, rompiscatole, sdrucciolevoli? Cambiali! Sono lagnosi, bavettosi, chiacchierosi, scaccolosi, noiosi? Cambiali! Ti scocciano, non li sopporti, non ti ascoltano, mettono a posto la tua stanza, calpestano i tuoi giocattoli, si rifiutano di lasciarti la casa, ti portano in Wacanza? Cambia genitori! Con Catalogo©, niente è più facile: scegli i genitori che preferisci, compila il buono d\'ordine, invialo e in meno di quarantotto ore Catalogo© ti consegna i tuoi nuovi genitori, per te, a domicilio, a casa tua! \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n'),
(195, 'La valigetta dell\'artista', 'Alessandra Falconi', '2020', '233', 23, 'Educazione', 'Scolastici', '9788859023746', 30.88, 'Sviluppare la creatività dei bambini e delle bambine, sin dall\'inizio del percorso scolastico, è appassionante anche per gli insegnanti. Si tratta di progettare situazioni ed esperienze in cui i più piccoli possano rivelarsi, mostrarsi, scoprirsi e conoscere meglio la realtà in cui vivono. Il corpo ha un ruolo fondamentale, le mani guidano il gioco. Hervè Tullet è un maestro nell\'accompagnare bambini, bambine e insegnanti nel divertirsi con forme, colori, segni, gesti.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n'),
(196, 'Autismo. Cosa fare (e non)', 'Marco Pontis', '2021', '150', 7, 'Educazione', 'Scolastici', '9788859024187', 15.68, 'Avete mai pensato alle strategie più efficaci per gestire un bambino con autismo a scuola? Questo libro, nello stile di un quaderno di Teacher Training, presenta suggerimenti e indicazioni per affrontare con successo 15 situazioni problematiche e comportamenti tipici del Disturbo dello Spettro dell\'autismo. Perché il bambino con autismo ha comportamenti problematici, ma non è un bambino problematico.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n'),
(197, 'Primi voli in lettura', 'Camillo Bortolato', '2015', '120', 4, 'Educazione', 'Scolastici', '9788859008965', 14.06, 'Molti bambini, già nella scuola dell\'infanzia, si avvicinano alla lettura da soli, attratti e incuriositi dal codice alfabetico. Il cofanetto presenta due strumenti per agevolare questo apprendimento spontaneo, preservando il carattere di scoperta e di gioco: \"un Abecedario con delle lettere speciali, da leggere e scoprire tutte insieme\" la Piccola storia di Pitti, con la quale il bambino, con le proprie strategie, può cimentarsi nelle prime esperienze di lettura, anche grazie all\'aiuto della striscia speciale dell\'alfabeto. \"Primi voli in lettura\" vuole rispettare la spontaneità e il desiderio dei bambini di apprendere e non si pone gli obiettivi di apprendimento della scuola primaria.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n'),
(200, 'I segreti della montagna', 'Dario Bianchi', '2022', '189', 8, 'Letteratura', 'Antologie', '9788859008968', 17.99, 'Una raccolta di racconti d\'avventura scritti da vari autori, curata da Dario Bianchi.'),
(201, 'Il tesoro dei sogni', 'Eleonora Moretti', '2022', '287', 19, 'Letteratura', 'Antologie', '9788859008969', 20.99, 'Una raccolta di racconti fantastici scritti da vari autori, curata da Eleonora Moretti.'),
(202, 'Le voci del mare', 'Francesco Ferrari', '2022', '567', 11, 'Letteratura', 'Antologie', '9788859008970', 22.99, 'Una raccolta di racconti e poesie sulla tematica del mare scritti da vari autori, curata da Francesco Ferrari.'),
(203, 'I misteri della giungla', 'Giorgia Russo', '2022', '423', 13, 'Letteratura', 'Antologie', '9788859008971', 25.99, 'Una raccolta di racconti d\'avventura scritti da vari autori, curata da Giorgia Russo.'),
(204, 'La forza del vento', 'Marco Bianchi', '2021', '189', 10, 'Letteratura', 'Antologie', '9788859008972', 17.99, 'Una raccolta di poesie e racconti sulla tematica del vento scritti da vari autori, curata da Marco Bianchi.'),
(205, 'La magia della luna', 'Nina Moretti', '2021', '287', 12, 'Letteratura', 'Antologie', '9788859008973', 19.99, 'Una raccolta di racconti fantastici scritti da vari autori, curata da Nina Moretti.');

-- --------------------------------------------------------

--
-- Struttura della tabella `ordine`
--

CREATE TABLE `ordine` (
  `metodo` varchar(255) NOT NULL,
  `data` date NOT NULL,
  `codice` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `ordine`
--

INSERT INTO `ordine` (`metodo`, `data`, `codice`, `username`) VALUES
('Carta di credito', '2023-02-14', '1MOGQ5521A', 'a'),
('Carta di credito', '2023-02-14', '3I34HBSLO6', 'emety39'),
('Bancomat', '2023-02-17', '5Q9QHL8L0P', 'a'),
('Contanti', '2023-02-14', '8AJ8RELIQC', 'emety39'),
('Contanti', '2023-02-15', 'BUU4VF33E0', 'a'),
('Contanti', '2023-02-13', 'ELVKOFJB73', 'a'),
('Carta di credito', '2023-02-14', 'JGPOK8OQAO', 'a'),
('Contanti', '2023-02-20', 'M3LJ0KGB13', 'a'),
('Contanti', '2023-02-15', 'OESSBIE9GE', 'a'),
('Carta di credito', '2023-02-16', 'OL09BLSRED', 'a'),
('Bancomat', '2023-02-15', 'PJVV1OJ058', 'a'),
('Contanti', '2023-02-15', 'SVO5KGEVEP', 'a'),
('Bancomat', '2023-02-14', 'TNRJ3S3IMJ', 'a'),
('Carta di credito', '2023-02-15', 'UT3P9TR4A3', 'a'),
('Carta di credito', '2023-02-14', 'VCVV9OI8HJ', 'a'),
('Carta di credito', '2023-02-15', 'VG5HBJ1RL2', 'a');

-- --------------------------------------------------------

--
-- Struttura della tabella `prodotto_ordine`
--

CREATE TABLE `prodotto_ordine` (
  `id` int(11) NOT NULL,
  `codice_ordine` varchar(255) NOT NULL,
  `qta` int(4) NOT NULL,
  `prezzo` float NOT NULL,
  `autore` varchar(40) NOT NULL,
  `titolo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `prodotto_ordine`
--

INSERT INTO `prodotto_ordine` (`id`, `codice_ordine`, `qta`, `prezzo`, `autore`, `titolo`) VALUES
(7, 'ELVKOFJB73', 1, 10.45, 'Zerocalcare', 'A Babbo morto. Una storia di Natale'),
(8, 'ELVKOFJB73', 1, 17.11, 'Sandro Landro', 'A Milano e a Napoli'),
(9, 'ELVKOFJB73', 1, 18.05, 'Giuseppe Vitrani', 'CAD e Decreto «Semplificazioni»'),
(10, 'JGPOK8OQAO', 1, 27.55, 'Carlo De Maria', 'Alessandro Schiavi'),
(11, 'JGPOK8OQAO', 1, 15.68, 'Marco Pontis', 'Autismo. Cosa fare (e non)'),
(13, 'TNRJ3S3IMJ', 3, 27.92, 'Serena Sensini', 'Basi di dati'),
(14, '8AJ8RELIQC', 1, 13.33, 'Miguel Murugarren', 'Bestiario universale del professor Revillod'),
(15, '8AJ8RELIQC', 1, 24.51, 'Claude Ponti', 'Catalogo dei genitori'),
(17, '3I34HBSLO6', 1, 17.11, 'Apollo Edizioni', 'Biografia di donne famose'),
(18, '3I34HBSLO6', 1, 12.26, 'Zack Keller', 'Cuphead. 1'),
(19, '3I34HBSLO6', 1, 24.51, 'Claude Ponti', 'Catalogo dei genitori'),
(20, '3I34HBSLO6', 1, 13.33, 'Miguel Murugarren', 'Bestiario universale del professor Revillod'),
(24, 'VCVV9OI8HJ', 3, 27.92, 'Serena Sensini', 'Basi di dati'),
(25, 'VCVV9OI8HJ', 2, 17.11, 'Sandro Landro', 'A Milano e a Napoli'),
(27, '1MOGQ5521A', 5, 17.99, 'White Star', 'Alan Turing. La nascita della scienza informatica'),
(28, 'UT3P9TR4A3', 8, 17.99, 'White Star', 'Alan Turing. La nascita della scienza informatica'),
(29, 'UT3P9TR4A3', 1, 24.51, 'Claude Ponti', 'Catalogo dei genitori'),
(30, 'UT3P9TR4A3', 1, 8.36, 'Miriana Caldart', 'Erbette che passione!'),
(31, 'UT3P9TR4A3', 10, 17.11, 'Sandro Landro', 'A Milano e a Napoli'),
(35, 'BUU4VF33E0', 10, 27.92, 'Serena Sensini', 'Basi di dati'),
(36, 'OESSBIE9GE', 5, 27.92, 'Serena Sensini', 'Basi di dati'),
(37, 'PJVV1OJ058', 2, 27.92, 'Serena Sensini', 'Basi di dati'),
(38, 'VG5HBJ1RL2', 3, 27.92, 'Serena Sensini', 'Basi di dati'),
(39, 'VG5HBJ1RL2', 1, 18.05, 'Giuseppe Vitrani', 'CAD e Decreto «Semplificazioni»'),
(41, 'SVO5KGEVEP', 6, 11.49, 'Marvel', 'Avengers. Uniti!'),
(42, 'OL09BLSRED', 1, 27.92, 'Serena Sensini', 'Basi di dati'),
(43, 'OL09BLSRED', 2, 9.41, 'Zerocalcare', 'Asterix e il grifone '),
(45, '5Q9QHL8L0P', 1, 24.51, 'Claude Ponti', 'Catalogo dei genitori'),
(46, 'M3LJ0KGB13', 1, 21.95, 'Sid Jacobson', 'Anne Frank');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Indici per le tabelle `carrello`
--
ALTER TABLE `carrello`
  ADD PRIMARY KEY (`id_carr`);

--
-- Indici per le tabelle `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indici per le tabelle `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `ordine`
--
ALTER TABLE `ordine`
  ADD PRIMARY KEY (`codice`);

--
-- Indici per le tabelle `prodotto_ordine`
--
ALTER TABLE `prodotto_ordine`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `carrello`
--
ALTER TABLE `carrello`
  MODIFY `id_carr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=126;

--
-- AUTO_INCREMENT per la tabella `libro`
--
ALTER TABLE `libro`
  MODIFY `ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=209;

--
-- AUTO_INCREMENT per la tabella `prodotto_ordine`
--
ALTER TABLE `prodotto_ordine`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
