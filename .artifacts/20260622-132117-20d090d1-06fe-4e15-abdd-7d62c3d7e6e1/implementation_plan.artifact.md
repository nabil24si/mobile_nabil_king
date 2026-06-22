# Implementation Plan - New Features: Sengketa and Peta Persil

This plan outlines the steps to add "Sengketa" (Disputes) and "Peta Persil" (Parcel Map) features, integrating Room Database, Camera/Scanner functionality, and Local Notifications.

## User Review Required

> [!IMPORTANT]
> **API Keys**: Implementing Google Maps for "Peta Persil" requires a Google Maps API Key. If no key is provided, I will implement a placeholder or a WebView-based OpenStreetMap for demonstration.
> **Scanning Logic**: I will implement the QR/Barcode scanner to auto-fill the "Kode Persil" or "NIK Warga" in the Sengketa form.

## Proposed Changes

### Configuration & Resources

#### [libs.versions.toml](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/gradle/libs.versions.toml)
- Add versions and libraries for CameraX, ML Kit Barcode Scanning, and Google Maps.

#### [build.gradle.kts](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/app/build.gradle.kts)
- Add the new dependencies.

#### [colors.xml](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/app/src/main/res/values/colors.xml)
- Define the project's color palette (Teal shades) for consistency.

---

### Data Layer (Room Database)

#### [NEW] [SengketaEntity.kt](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/app/src/main/java/com/example/nabil_king/data/entity/SengketaEntity.kt)
- Entity for Dispute data: id, title, description, party1, party2, parcelCode, status, createdAt.

#### [NEW] [SengketaDao.kt](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/app/src/main/java/com/example/nabil_king/data/dao/SengketaDao.kt)
- DAO for CRUD operations on disputes.

#### [AppDatabase.kt](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/app/src/main/java/com/example/nabil_king/data/AppDatabase.kt)
- Add `SengketaEntity` to the database and provide the DAO.

---

### Feature: Sengketa (Disputes)

#### [NEW] [DataSengketaFragment.kt](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/app/src/main/java/com/example/nabil_king/Home/Layanan/Sengketa/DataSengketaFragment.kt)
- Fragment to display a list of disputes from Room.

#### [NEW] [SengketaAdapter.kt](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/app/src/main/java/com/example/nabil_king/Home/Layanan/Sengketa/SengketaAdapter.kt)
- Adapter for the dispute list.

#### [NEW] [AddSengketaActivity.kt](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/app/src/main/java/com/example/nabil_king/Home/Layanan/Sengketa/AddSengketaActivity.kt)
- Form to add a new dispute. Includes a button to launch the Scanner.

---

### Feature: Peta Persil (Parcel Map)

#### [NEW] [PetaPersilFragment.kt](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/app/src/main/java/com/example/nabil_king/Home/Layanan/Peta/PetaPersilFragment.kt)
- Fragment showing a map view.

---

### Utilities (Scanner & Notifications)

#### [NEW] [ScannerActivity.kt](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/app/src/main/java/com/example/nabil_king/util/ScannerActivity.kt)
- Generic Activity using CameraX and ML Kit to scan QR/Barcodes.

#### [NEW] [NotificationHelper.kt](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/app/src/main/java/com/example/nabil_king/util/NotificationHelper.kt)
- Helper class to create notification channels and send reminders.

---

### UI Integration

#### [TabsAdapter.kt](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/app/src/main/java/com/example/nabil_king/Home/Layanan/TabsAdapter.kt)
- Update to include 4 tabs: Warga, Persil, Sengketa, Peta.

#### [LayananActivity.kt](file:///C:/Users/nabil/StudioProjects/mobile_nabil_king/app/src/main/java/com/example/nabil_king/Home/Layanan/LayananActivity.kt)
- Update tab titles and logic for 4 tabs.

## Verification Plan

### Automated Tests
- Build the project: `gradlew app:assembleDebug`

### Manual Verification
1. Open **Layanan Perdes** and verify all 4 tabs are present.
2. Go to **Sengketa** tab and add a new dispute.
3. Test the **Scanner** button in the Sengketa form (Camera permission check).
4. Verify the new dispute is saved in the list (Room check).
5. Check if a **Notification** appears when a dispute is added.
6. Open **Peta Persil** and verify the map/placeholder loads.
