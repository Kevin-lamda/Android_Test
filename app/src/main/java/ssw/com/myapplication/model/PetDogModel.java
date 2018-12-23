package ssw.com.myapplication.model;

/**
 * "id": "10717442",
 *                 "petId": "1898046000587399220",
 *                 "rareDegree": 0,
 *                 "generation": 0,
 *                 "petName": "小莱",
 *                 "petUrl": "https://blockchain-pet-online.cdn.bcebos.com/PET_SVG_543ad1c7b3b0a55de4c6b53a295be25d",
 *                 "bgColor": "#B3E5FC",
 *                 "coolingInterval": "24小时",
 *                 "shelfStatus": 0,
 *                 "chainStatus": 1,
 *                 "amount": "0.00",
 *                 "lastBreedTime": 1533002876000,
 *                 "newBreeded": false,
 *                 "lockStatus": 0,
 *                 "isCooling": false
 */
public class PetDogModel {
    private String id;
    private String petId;
    private String rareDegree;
    private String generation;
    private String petName;
    private String petUrl;
    private String bgColor;
    private String coolingInterval;
    private String shelfStatus;
    private String chainStatus;
    private String amount;
    private String lastBreedTime;
    private String newBreeded;
    private String lockStatus;
    private String isCooling;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getRareDegree() {
        return rareDegree;
    }

    public void setRareDegree(String rareDegree) {
        this.rareDegree = rareDegree;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetUrl() {
        return petUrl;
    }

    public void setPetUrl(String petUrl) {
        this.petUrl = petUrl;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getCoolingInterval() {
        return coolingInterval;
    }

    public void setCoolingInterval(String coolingInterval) {
        this.coolingInterval = coolingInterval;
    }

    public String getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(String shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public String getChainStatus() {
        return chainStatus;
    }

    public void setChainStatus(String chainStatus) {
        this.chainStatus = chainStatus;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLastBreedTime() {
        return lastBreedTime;
    }

    public void setLastBreedTime(String lastBreedTime) {
        this.lastBreedTime = lastBreedTime;
    }

    public String getNewBreeded() {
        return newBreeded;
    }

    public void setNewBreeded(String newBreeded) {
        this.newBreeded = newBreeded;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getIsCooling() {
        return isCooling;
    }

    public void setIsCooling(String isCooling) {
        this.isCooling = isCooling;
    }
}
