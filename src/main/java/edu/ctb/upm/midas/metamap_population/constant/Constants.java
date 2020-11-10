package edu.ctb.upm.midas.metamap_population.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 27/3/17.
 * @project ExtractionInformationWikipedia
 * @version ${<VERSION>}
 * @author Gerardo Lagunes G.
 * @className Constants
 * @see
 */
@Component
public class Constants {

    public static final String DOT_JSON = ".json";
    public static final String DOT_XML = ".xml";

    /**
     * Fuentes de extracción
     * */
    public static final String SOURCE_WIKIPEDIA = "wikipedia";
    public static final String SOURCE_WIKIPEDIA_CODE = "SO01";

    public static final String SOURCE_PUBMED = "pubmed";
    public static final String SOURCE_PUBMED_CODE = "SO02";

    public static final String SOURCE_MAYOCLINIC = "mayoclinic";
    public static final String SOURCE_MAYOCLINIC_CODE = "SO03";

    public static final String ABSTRACT_SECTION = "abstract";

    public static final String MESH_RESOURCE_NAME = "MeSH";

    /**
     * Constantes para la validación de conceptos (metamap y proceso de validación de terminos)
     */
    public static final String METAMAP_FOLDER = "tmp/mm/";
    public static final String METAMAP_FILE_NAME = "_metamap_filter";
    public static final String TVP_RETRIEVAL_HISTORY_FOLDER = "tmp/tvp/";
    public static final String TVP_RETRIEVAL_FILE_NAME = "_tvp_validation";
    public static final String PM_RETRIEVAL_DOCUMENT_FILE_NAME = "_document_pubmed_retrieval";
    public static final String PM_RETRIEVAL_DISEASE_LIST_FILE_NAME = "_disease_list_pubmed_retrieval";


    /**
     * Constantes para la validación de conceptos (metamap y proceso de validación de terminos)
     */

    public static final String CONSULT_SOURCE_ALL = "all";
    public static final String CONSULT_LAST_SNAPSHOT = "last";

    /**
     * Fecha por default
     */
    @SuppressWarnings("deprecation")
    public static final Date DEFAULT_DATE = new Date(70, 0, 1);

    /**
     * Cadena en blancos
     */
    public static final String BLANKS = "";

    /**
     * Punto
     */
    public static final String POINT = ".";

    /**
     * Coma
     */
    public static final String COMMA = ",";

    /**
     * Comillas (quotation marks)
     */
    public static final String QUOTATION_MARKS = "\"";

    /**
     * Punto y coma
     */
    public static final String COMMA_DOT = ";";

    /**
     * Cero
     */
    public static final String ZERO = "0";

    /**
     * Slash
     */
    public static final String SLASH = "/";

    /**
     * Linea del piso
     */
    public static final String UNDER_SCORE = "_";

    /**
     * Guion
     */
    public static final String DASH = "-";

    /**
     * Uno
     */
    public static final String ONE = "1";

    /**
     * Tiempo estandar para dormir y esperar
     */
    public static final long SLEEP_TIME = 15000;

    /**
     * Menos
     */
    public static final String MINUS = "-";
    /**
     * Menos Cero
     */
    public static final String MINUS_ZERO = "-0";

    /**
     * Parametro de Url 1
     */
    public static final String URL_PARAM01 = "&1";

    /**
     * Parametro de Url 2
     */
    public static final String URL_PARAM02 = "&2";

    /**
     * Dos puntos
     */
    public static final String TWO_POINTS = ":";

    /**
     * Dos puntos seguido de cero
     */
    public static final String TWO_POINTS_ZERO = ":0";

    /**
     * Tres
     */
    public static final String THREE = "3";

    /**
     * Dos
     */
    public static final String TWO = "2";

    /**
     * Separador decimal para split
     */
    public static final String DECIM_SEP = "\\.";

    /**
     * Punto decimal
     */
    public static final String DEC_POINT = ".";

    /**
     * Espacio en blancos
     */
    public static final String BLANK_SPACE = " ";

    /**
     * Cinco
     */
    public static final String FIVE = "5";

    /**
     * Cuatro
     */
    public static final String FOUR = "4";

    /**
     * Ceros
     */
    public static final String ZEROS = "00";

    /**
     * Parametro 01
     */
    public static final String PARAM01 = "01";

    /**
     * Parametro 02
     */
    public static final String PARAM02 = "02";

    /**
     * Parametro 03
     */
    public static final String PARAM03 = "03";

    /**
     * Separador interno de parametros
     */
    public static final String INNER_PARAM_SEP = "_";

    /**
     * Flecha
     */
    public static final String ARROW = "==>";

    /**
     * Parentesis izquierdo
     */
    public static final String LEFT_PARENTHESIS = "[";

    /**
     * Parentesis derecho
     */
    public static final String RIGHT_PARENTHESIS = "]";

    /**
     * Porcentaje
     */
    public static final String PERCENTAGE = "%";

    /**
     * Punto
     */
    public static final String DOT = ".";

    /**
     * Add
     */
    public static final String ADD = "ADD";

    /**
     * Change
     */
    public static final String CHANGE = "CHANGE";

    /**
     * Delete
     */
    public static final String DELETE = "DELETE";

    /**
     * Ampersand
     */
    public static final String AMPERSAND = "&";

    /**
     * Init Change
     */
    public static final String INIT_CHANGE = "initChange";

    /**
     * Stdfunreq
     */
    public static final String STDFUNREQ = "stdfunreq";

    /**
     * Init Add
     */
    public static final String INIT_ADD = "initAdd";

    /**
     * Bean de autenticacion
     */
    public static final String AUTENTICATION_BEAN = "autenticationBean";

    /**
     * Nombre del subfile
     */
    public static final String NAME_SUBFILE = "subfile";

    /**
     * Parametro SE01 (Maximo intentoss inicio sesion)
     */
    public static final String SE01 = "SE01";

    /**
     * Parametro SE02 (Registros a mostrar en subfile)
     */
    public static final String SE02 = "SE02";

    /**
     * Parametro SE03 (Paginas en subfile)
     */
    public static final String SE03 = "SE03";

    /**
     * Parametro SE04 (Minimo caracteres contrase?a)
     */
    public static final String SE04 = "SE04";

    /**
     * Parametro SE05 (Dias para cambiar contrase?a)
     */
    public static final String SE05 = "SE05";

    /**
     * Parametro SE06 (Tiempo de bloqueo de usuario minutos)
     */
    public static final String SE06 = "SE06";

    /**
     * Parametro SE07 (Maximo de registros en consulta)
     */
    public static final String SE07 = "SE07";

    /**
     * Parametro SE08 (Direcci?n Endpoint Virtuoso)
     */
    public static final String SE08 = "SE08";

    /**
     * Parametro SE09 (Maximo de registros en autocompletar)
     */
    public static final String SE09 = "SE09";

    /**
     * Parametro SE10 (Url del servicio de visualizacion)
     */
    public static final String SE10 = "SE10";

    /**
     * Parametro SE11 (Indicador de visualizaci?n integrada)
     */
    public static final String SE11 = "SE11";

    /**
     * Nombre de Bean
     */
    public static final Object BEAN_NAME = "beanName";

    /**
     * Timer de tareas automaticas
     */
    public static final String TIMER = "TIMER_";

    /**
     * Seleccione
     */
    public static final String SELECCIONE = "seleccione";

    /**
     * Parametro
     */
    public static final String PARAMETER = "parameter";

    /**
     * Parametro 01 bean
     */
    public static final String BEAN_PARAM01 = "param1";

    /**
     * Parametro 02 bean
     */
    public static final String BEAN_PARAM02 = "param2";

    /**
     * SMTP
     */
    public static final String SMTP = "smtp";

    /**
     * Dias en la semana
     */
    public static final int DAYS_IN_WEEK = 7;

    /**
     * Hora en milisegundos
     */
    public static final long HOUR_MILIS = 3600000 * 23;

    /**
     * Nueva linea
     */
    public static final String NEW_LINE = "\n";

    /**
     * Atributo de Relaci?n
     */
    public static final String REL = "?rel_";

    /**
     * Objeto de query
     */
    public static final String OBJECT = "object";

    /**
     * Menor
     */
    public static final String MINOR = "<";

    /**
     * Mayor
     */
    public static final String MAJOR = ">";

    /**
     * Separador de mail (@)
     */
    public static final String MAIL_SEP = "@";

    /**
     * ID del paginador de listas
     */
    public static final String LIST_PAGINATOR = "listPaginator";

    /**
     * Subfile
     */
    public static final String SUBFILE = "subfile";

    /**
     * Ruta para imagen de hoja
     */
    public static final String PATH_LEAF = "/PhiBaseWeb/xmlhttp/css/rime/css-images/tree_document.gif";

    /**
     * Ruta para imagen de folder open
     */
    public static final String PATH_FOLDER_OPEN = "/PhiBaseWeb/xmlhttp/css/rime/css-images/tree_folder_closed.gif";

    /**
     * Ruta para imagen de folder close
     */
    public static final String PATH_FOLDER_CLOSE = "/PhiBaseWeb/xmlhttp/css/rime/css-images/tree_folder_open.gif";

    /**
     * Nodo Raiz
     */
    public static final String ROOT_NODE = "RootNode";

    /**
     * Ruta icono no encontrado
     */
    public static final String ICON_NOTFOUND = "/PhiBaseWeb/images/btn/vineta1.gif";

    /**
     * Ruta icono encontrado
     */
    public static final String ICON_FOUND = "/PhiBaseWeb/images/icons/normal/16/check_mark_16.png";

    /**
     * Query Result
     */
    public static final Object QUERY_RESULT = "queryResult";

    /**
     * Query Value
     */
    public static final Object QUERY_VALUE = "queryValue";

    /**
     * Gene
     */
    public static final String K00 = "00";

    /**
     * Host
     */
    public static final String K01 = "01";

    /**
     * Pathogen
     */
    public static final String K02 = "02";

    /**
     * Protocol Description
     */
    public static final String K03 = "03";

    /**
     * Interaction Context Mutant
     */
    public static final String K04 = "04";

    /**
     * Disease name
     */
    public static final String K05 = "05";

    /**
     * Gene Locus Id
     */
    public static final String K06 = "06";

    /**
     * Gene Function
     */
    public static final String K07 = "07";

    /**
     * Gene Name
     */
    public static final String K08 = "08";

    /**
     * Allele
     */
    public static final String K09 = "09";

    /**
     * Invitro Growth
     */
    public static final String K10 = "10";

    /**
     * Lethal Knockout
     */
    public static final String K11 = "11";

    /**
     * Gene Accession
     */
    public static final String K12 = "12";

    /**
     * Citation
     */
    public static final String K13 = "13";

    /**
     * InterOperator
     */
    public static final String INTER_OP = "InterOp:";

    /**
     * Link
     */
    public static final String LINK = "link";

    /**
     * Igual
     */
    public static final String EQUAL = "=";

    /**
     * Dollar
     */
    public static final String DOLLAR = "$";

    /**
     * Separador de parametros
     */
    public static final String PARAM_SEP = "<>";

    /**
     * Separador de parametros
     */
    public static final String PARAM_SEP_2 = "?";


    public final static String CHAR_SEPARATOR = "!";

    public final static String BLANK_SPACE_CODE = "%20";

    /**
     * Wikipedia API
     */
    public final static String SQUARE_BRACKETS_OPEN = "[";
    public final static String SQUARE_BRACKETS_CLOSE = "]";

    public final static String QUERY_ELEMENT_NAME = "query";

    public final static String PAGES_ELEMENT_NAME = "pages";
    public final static String PAGES_ELEMENT_PAGEID_NAME = "pageid";
    public final static String PAGES_ELEMENT_TITLE_NAME = "title";

    public final static String REVISIONS_ELEMENT_NAME = "revisions";
    public final static String REVISIONS_ELEMENT_REVID_NAME = "revid";
    public final static String REVISIONS_ELEMENT_PARENTID_NAME = "parentid";
    public final static String REVISIONS_ELEMENT_MINOR_NAME = "minor";
    public final static String REVISIONS_ELEMENT_USER_NAME = "user";
    public final static String REVISIONS_ELEMENT_USERID_NAME = "userid";
    public final static String REVISIONS_ELEMENT_TIMESTAMP_NAME = "timestamp";
    public final static String REVISIONS_ELEMENT_SIZE_NAME = "size";
    public final static String REVISIONS_ELEMENT_COMMENT_NAME = "comment";
    public final static String REVISIONS_ELEMENT_TEXT_NAME = "text";

    public final static String REDIRECTS_ELEMENT_NAME = "redirects";


    /**
     * Rutas
     */
    public final static String VALIDATED_FOLDER = "cnv_data/filter/validated/";
    public final static String NO_VALIDATED_FOLDER = "cnv_data/filter/not_validated/";
    public final static String VALIDATION_FINDINGS_FILE = "vte_data/results/allFindings.fd";
    public final static String DISEASES_FINDINGS_FOLDER = "cnv_data/diseases_findings/";
    public final static String DISEASES_URLS_FILE = "cnv_data/diseases.lst";
    public final static String DISEASES_TEXTS_FOLDER = "cnv_data/diseasesData/";
    public final static String VALIDATION_FINDINGS_TEMP_FOLDER = "vte_data/temp_findings/";


    public final static String ERR_NO_PARAMETER = "No parameter was sent";
    public final static String ERR_EMPTY_PARAMETER = "Empty parameter";



}
