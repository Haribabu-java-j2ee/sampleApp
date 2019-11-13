package gov.utcourts.coriscommon.util;

import gov.utcourts.coriscommon.dto.KaseDTO;
import gov.utcourts.coriscommon.enumeration.CaseCategory;

public class KaseTitleUtils {

    private static final String IN_THE_MATTER = "IN THE MATTER";
    private static final String IN_RE = "IN RE";
    private static final String IN_THE_MATTER_OF = "IN THE MATTER OF ";
    private static final String COMMA = ", ";
    private static final String VS = "  vs.  ";
    private static final String ET_AL = ", et al.";
    private static final String ET_AL_VS = ", et al.  vs.  ";

    /**
     * Sets the Case Title for the kaseDTO
     * 
     * @param kaseDTO
     *            kaseDTO object
     * @return String The Case Title
     * @throws Exception
     */
    public static void processCategory(KaseDTO kaseDTO) throws Exception {

        switch (CaseCategory.resolveEnumFromString(kaseDTO.getCaseTypeCategory())) {
        case CIVIL:
        case DOMESTIC:
        case SMALLCLAIMS:
            if (!isCategoryInVoluntaryCommitment(kaseDTO)) {
                processCaseParties(kaseDTO);
            }
            break;
        case CRIMINAL:
            processCaseParties(kaseDTO);
            break;
        case PROBATE:
            if (TextUtil.isEmpty(kaseDTO.getCaseTitle())) {
                processNullTitle(kaseDTO);
            }
            break;
        default:
            // If the Case Type is IC then call the same code for Probate title
            if ("IC".equalsIgnoreCase(kaseDTO.getCaseType())) {
                if (TextUtil.isEmpty(kaseDTO.getCaseTitle())) {
                    processNullTitle(kaseDTO);
                }
            } else {
            	kaseDTO.setCaseTitle("*N/A*");
            }
            break;
        }
    }

    private static boolean isCategoryInVoluntaryCommitment(KaseDTO kaseDTO) {
        boolean isInVoluntaryCommitment = false;
        if ("IC".equalsIgnoreCase(kaseDTO.getCaseType())) {
            isInVoluntaryCommitment = true;
        }
        return isInVoluntaryCommitment;
    }

    /**
     * Builds the Case Title from the party_case and party data.
     * 
     * @param kaseDTO
     * @throws Exception
     */
    public static void processCaseParties(KaseDTO kaseDTO) throws Exception {

        if (!TextUtil.isEmpty(kaseDTO.getPlaintiffFirstName()) && !TextUtil.isEmpty(kaseDTO.getDefendantFirstName())) {
            processPlaFirstNameDefFirstName(kaseDTO);

        } else if (TextUtil.isEmpty(kaseDTO.getPlaintiffFirstName()) && TextUtil.isEmpty(kaseDTO.getDefendantFirstName())) {
            processNullPlaFirstNameNullDefFirstName(kaseDTO);

        } else if (TextUtil.isEmpty(kaseDTO.getPlaintiffFirstName())) {
            processNullPlaintiffFirstName(kaseDTO);

        } else if (TextUtil.isEmpty(kaseDTO.getDefendantFirstName())) {
            processNullDefendantFirstName(kaseDTO);
        }
    }

    /**
     * Creates the Case Title when we have both the Plaintiff and Defendants First names.
     * 
     * @param kaseDTO
     */
    private static void processPlaFirstNameDefFirstName(KaseDTO kaseDTO) {

        StringBuilder sb = new StringBuilder();

        if (kaseDTO.getDefendantCount() > 1 && kaseDTO.getPlaintiffCount() > 1) {
            sb.append(kaseDTO.getPlaintiffLastName());
            sb.append(COMMA);
            sb.append(kaseDTO.getPlaintiffFirstName());
            sb.append(ET_AL_VS);
            sb.append(kaseDTO.getDefendantLastName());
            sb.append(COMMA);
            sb.append(kaseDTO.getDefendantFirstName());
            sb.append(ET_AL);

        } else if (kaseDTO.getPlaintiffCount() > 1) {
            sb.append(kaseDTO.getPlaintiffLastName());
            sb.append(COMMA);
            sb.append(kaseDTO.getPlaintiffFirstName());
            sb.append(ET_AL_VS);
            sb.append(kaseDTO.getDefendantLastName());
            sb.append(COMMA);
            sb.append(kaseDTO.getDefendantFirstName());

        } else if (kaseDTO.getDefendantCount() > 1) {
            sb.append(kaseDTO.getPlaintiffLastName());
            sb.append(COMMA);
            sb.append(kaseDTO.getPlaintiffFirstName());
            sb.append(VS);
            sb.append(kaseDTO.getDefendantLastName());
            sb.append(COMMA);
            sb.append(kaseDTO.getDefendantFirstName());
            sb.append(ET_AL);

        } else {
            sb.append(kaseDTO.getPlaintiffLastName());
            sb.append(COMMA);
            sb.append(kaseDTO.getPlaintiffFirstName());
            sb.append(VS);
            sb.append(kaseDTO.getDefendantLastName());
            sb.append(COMMA);
            sb.append(kaseDTO.getDefendantFirstName());

        }

        if (sb.length() > 0) {
            kaseDTO.setCaseTitle(sb.toString());
        }
    }

    private static void processNullDefendantFirstName(KaseDTO kaseDTO) {

        if (TextUtil.isEmpty(kaseDTO.getDefendantLastName())) {
            processNullTitleDefPartyCountZero(kaseDTO);
        } else {
            StringBuilder sb = new StringBuilder();

            if (kaseDTO.getDefendantCount() > 1 && kaseDTO.getPlaintiffCount() > 1) {
                sb.append(kaseDTO.getPlaintiffLastName());
                sb.append(COMMA);
                sb.append(kaseDTO.getPlaintiffFirstName());
                sb.append(ET_AL_VS);
                sb.append(kaseDTO.getDefendantLastName());
                sb.append(ET_AL);

            } else if (kaseDTO.getPlaintiffCount() > 1) {
                sb.append(kaseDTO.getPlaintiffLastName());
                sb.append(COMMA);
                sb.append(kaseDTO.getPlaintiffFirstName());
                sb.append(ET_AL_VS);
                sb.append(kaseDTO.getDefendantLastName());

            } else if (kaseDTO.getDefendantCount() > 1) {
                sb.append(kaseDTO.getPlaintiffLastName());
                sb.append(COMMA);
                sb.append(kaseDTO.getPlaintiffFirstName());
                sb.append(VS);
                sb.append(kaseDTO.getDefendantLastName());
                sb.append(ET_AL);
            } else {
                // This needs to be fixed.
                sb.append(kaseDTO.getPlaintiffLastName());
                sb.append(COMMA);
                sb.append(kaseDTO.getPlaintiffFirstName());
                sb.append(VS);
                sb.append(kaseDTO.getDefendantLastName());

            }

            if (sb.length() > 0) {
            	kaseDTO.setCaseTitle(sb.toString());
            }
        }
    }

    private static void processNullPlaintiffFirstName(KaseDTO kaseDTO) {

        StringBuilder sb = new StringBuilder();

        if (kaseDTO.getDefendantCount() > 1 && kaseDTO.getPlaintiffCount() > 1) {
            sb.append(kaseDTO.getPlaintiffLastName());
            sb.append(ET_AL_VS);
            sb.append(kaseDTO.getDefendantLastName());
            sb.append(COMMA);
            sb.append(kaseDTO.getDefendantFirstName());
            sb.append(ET_AL);

        } else if (kaseDTO.getPlaintiffCount() > 1) {
            sb.append(kaseDTO.getPlaintiffLastName());
            sb.append(ET_AL_VS);
            sb.append(kaseDTO.getDefendantLastName());
            sb.append(COMMA);
            sb.append(kaseDTO.getDefendantFirstName());

        } else if (kaseDTO.getDefendantCount() > 1) {
            sb.append(kaseDTO.getPlaintiffLastName());
            sb.append(VS);
            sb.append(kaseDTO.getDefendantLastName());
            sb.append(COMMA);
            sb.append(kaseDTO.getDefendantFirstName());
            sb.append(ET_AL);

        } else {
            sb.append(kaseDTO.getPlaintiffLastName());
            sb.append(VS);
            sb.append(kaseDTO.getDefendantLastName());
            sb.append(COMMA);
            sb.append(kaseDTO.getDefendantFirstName());

        }

        if (sb.length() > 0) {
        	kaseDTO.setCaseTitle(sb.toString());
        }
    }

    private static void processNullPlaFirstNameNullDefFirstName(KaseDTO kaseDTO) {

        if (TextUtil.isEmpty(kaseDTO.getDefendantLastName())) {
            processNullTitleDefPartyCountZero(kaseDTO);
        } else {
            StringBuilder sb = new StringBuilder();

            if (kaseDTO.getDefendantCount() > 1 && kaseDTO.getPlaintiffCount() > 1) {
                sb.append(kaseDTO.getPlaintiffLastName());
                sb.append(ET_AL_VS);
                sb.append(kaseDTO.getDefendantLastName());
                sb.append(ET_AL);

            } else if (kaseDTO.getPlaintiffCount() > 1) {
                sb.append(kaseDTO.getPlaintiffLastName());
                sb.append(ET_AL_VS);
                sb.append(kaseDTO.getDefendantLastName());

            } else if (kaseDTO.getDefendantCount() > 1) {
                sb.append(kaseDTO.getPlaintiffLastName());
                sb.append(VS);
                sb.append(kaseDTO.getDefendantLastName());
                sb.append(ET_AL);

            } else {
                sb.append(kaseDTO.getPlaintiffLastName());
                sb.append(VS);
                sb.append(kaseDTO.getDefendantLastName());

            }

            if (sb.length() > 0) {
            	kaseDTO.setCaseTitle(sb.toString());
            }
        }
    }

    public static void processNullTitle(KaseDTO kaseDTO) throws Exception {

        if (kaseDTO.getDefendantCount() == 0) {
            processNullTitleDefPartyCountZero(kaseDTO);

        } else if (kaseDTO.getPlaintiffCount() == 0) {
            processNullTitlePlaPartyCountZero(kaseDTO);

        } else {
            processNullCaseTitle(kaseDTO);
        }
    }

    private static void processNullCaseTitle(KaseDTO kaseDTO) throws SystemException {

        if (TextUtil.isEmpty(kaseDTO.getCaseTitle())) {
            if (TextUtil.isEmpty(kaseDTO.getPlaintiffFirstName())) {
            	kaseDTO.setCaseTitle(IN_THE_MATTER_OF + kaseDTO.getPlaintiffLastName());
            } else {
            	kaseDTO.setCaseTitle(IN_THE_MATTER_OF + kaseDTO.getPlaintiffLastName() + ", " + kaseDTO.getPlaintiffFirstName());
            }
        }
    }

    private static void processNullTitlePlaPartyCountZero(KaseDTO kaseDTO) {

        if (TextUtil.isEmpty(kaseDTO.getDefendantFirstName())) {
            if (kaseDTO.getDefendantLastName().contains(IN_RE) || kaseDTO.getDefendantLastName().contains(IN_THE_MATTER)) {
            	kaseDTO.setCaseTitle(kaseDTO.getDefendantLastName());
            } else {
            	kaseDTO.setCaseTitle(IN_THE_MATTER_OF + kaseDTO.getDefendantLastName());
            }
        } else {
            if (kaseDTO.getDefendantLastName().contains(IN_RE) || kaseDTO.getDefendantLastName().contains(IN_THE_MATTER)) {
            	kaseDTO.setCaseTitle(kaseDTO.getDefendantLastName() + ", " + kaseDTO.getDefendantFirstName());
            } else {
            	kaseDTO.setCaseTitle(IN_THE_MATTER_OF + kaseDTO.getDefendantLastName() + ", " + kaseDTO.getDefendantFirstName());
            }
        }
    }

    /**
     * 
     * 
     * @param kaseDTO
     */
    private static void processNullTitleDefPartyCountZero(KaseDTO kaseDTO) {

        if (!TextUtil.isEmpty(kaseDTO.getDefendantLastName())) {

            if (TextUtil.isEmpty(kaseDTO.getPlaintiffFirstName())) {
                if (kaseDTO.getPlaintiffLastName().contains(IN_RE) || kaseDTO.getPlaintiffLastName().contains(IN_THE_MATTER)) {
                	kaseDTO.setCaseTitle(kaseDTO.getPlaintiffLastName());
                } else {
                	kaseDTO.setCaseTitle(IN_THE_MATTER_OF + kaseDTO.getPlaintiffLastName());
                }
            } else {
                if (kaseDTO.getPlaintiffLastName().contains(IN_RE) || kaseDTO.getPlaintiffLastName().contains(IN_THE_MATTER)) {
                	kaseDTO.setCaseTitle(kaseDTO.getPlaintiffLastName() + ", " + kaseDTO.getPlaintiffFirstName());
                } else {
                	kaseDTO.setCaseTitle(IN_THE_MATTER_OF + kaseDTO.getPlaintiffLastName() + ", " + kaseDTO.getPlaintiffFirstName());
                }
            }
        }
    }
}
