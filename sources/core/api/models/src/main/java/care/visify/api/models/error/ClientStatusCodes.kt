package care.visify.api.models.error

object ClientStatusCodes {

    enum class RemoteError(val code: Int) {

        UNKNOWN(-1),

        PHOTO_ALREADY_USED(100),
        PHOTO_MULTIPART_WRONG_PART_NAME(101),
        PHOTO_USER_HAS_NO_WRITE_PERMISSION(102),
        PHOTO_USER_HAS_NO_ADMIN_PERMISSION(103),
        PHOTO_CREATE_WRONG_ITEMS_COUNT(104),
        PHOTO_EXTERNAL_UPLOAD_FAILURE(105),
        PHOTO_NO_PERMISSION(106),
        PHOTO_ENTITY_MISSING(107),
        PHOTO_UNEXPECTED_BEHAVIOR(108),

        CHAT_ENTITY_MISSING(200),
        CHAT_MESSAGE_ENTITY_MISSING(201),
        CHAT_NO_PERMISSION(202),
        CHAT_MULTIPLE_ATTACHMENTS_NOT_ALLOWED(203),
        // 204 205
        MULTIPLE_USERS_CHATS_NOT_SUPPORTED(206),
        ORG_CANNOT_START_NEW_DIALOG(207),

        REGISTRATION_WRONG_CONFIRM_CODE(300),
        REGISTRATION_ENTITY_MISSING(301),
        REGISTRATION_CODE_ALREADY_CONFIRMED(303),
        REGISTRATION_ENTITY_EXISTS(304),
        REGISTRATION_ENTITY_NOT_FOUND(305),
        REGISTRATION_UNEXPECTED_BEHAVIOR(306),
        REGISTRATION_CONFIRM_CODE_NOT_ELAPSED(307),
        REGISTRATION_GOOGLE_TOKEN_EXPIRED(308),
        REGISTRATION_VK_AUTH_ERROR(309),
        REGISTRATION_USER_ENTITY_ALREADY_EXISTS(310), // move to User domain
        REGISTRATION_CONFIRM_CODE_NOT_CONFIRMED(311),

        ORDER_NO_RESPONSES_ERROR(400),
        ORDER_ENTITY_MISSING(401),
        ORDER_STATUS_CONFLICT(402),
        ORDER_CANNOT_BE_EDITED(403),
        ORDER_CANNOT_BE_FINISHED(404),
        ORDER_USER_ENTITY_MISSING(405), // replace with user-domain err
        ORDER_EMPLOYEE_ENTITY_MISSING(406), // replace with user-domain err
        ORDER_FAVOR_ENTITY_MISSING(407), // replace with favor-domain err
        ORDER_FAVOR_SUBCATEGORIES_CONFLICT(408),
        ORDER_PARTICULAR_EMPLOYEE_ID_MISSING(409),
        ORDER_UNCOMPLETED_CANT_BE_USED_FOR_PORTFOLIO(410),
        ORDER_CHANGES_CANNOT_BE_APPLIED(411),
        ORDER_PERMISSION_DENIED(412),
        ORDER_CHANGES_ENTITY_MISSING(413),
        ORDER_IS_NOT_A_VISIT(415),
        ORDER_NO_ACTIVE_CHANGES(423),

        ADDRESS_ENTITY_MISSING(500),
        ADDRESS_CITY_MISSING(501),
        ADDRESS_INVALID_ERROR(502),

        FEEDBACK_ENTITY_MISSING(600),
        FEEDBACK_SORT_TYPE_MISSING(601),
        FEEDBACK_NO_PERMISSION(602),
        FEEDBACK_WEARING_ENTITY_MISSING(603),
        FEEDBACK_TOO_MUCH_PHOTOS(604),

        EMPLOYEE_JOBS_ENTITY_MISSING(700),
        EMPLOYEE_NO_PERMISSION(701),
        EMPLOYEE_ALREADY_EXISTS(702),

        USER_ENTITY_MISSING(800),

        ORG_ENTITY_MISSING(900),

        FAVOR_ENTITY_MISSING(1000),

        CALENDAR_ENTITY_MISSING(1200),
        CALENDAR_TOO_LONG_RANGE(1201),
        CALENDAR_CLOSED_AT_DATE(1202),
        CALENDAR_NO_PERMISSION(1203),
    }

    object Failure {
        const val UNAUTHORIZED = 401
        const val NOT_FOUND = 404
    }
}

val Throwable?.visifyErrorCode: Int
    get() {
        if (this is InternalNetworkException) {
            return visifyErrorCode
        }
        return ClientStatusCodes.RemoteError.UNKNOWN.code
    }

val Throwable?.remoteError: ClientStatusCodes.RemoteError
    get() = ClientStatusCodes.RemoteError.values().first {
        it.code == visifyErrorCode
    }

val Throwable.isUnknown: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.UNKNOWN

val Throwable.isPhotoAlreadyUsed: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.PHOTO_ALREADY_USED

val Throwable.isPhotoMultipartWrongPartName: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.PHOTO_MULTIPART_WRONG_PART_NAME

val Throwable.isPhotoUserHasNoWritePermission: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.PHOTO_USER_HAS_NO_WRITE_PERMISSION

val Throwable.isPhotoUserHasNoAdminPermission: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.PHOTO_USER_HAS_NO_ADMIN_PERMISSION

val Throwable.isPhotoCreateWrongItemsCount: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.PHOTO_CREATE_WRONG_ITEMS_COUNT

val Throwable.isPhotoExternalUploadFailure: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.PHOTO_EXTERNAL_UPLOAD_FAILURE

val Throwable.isPhotoNoPermission: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.PHOTO_NO_PERMISSION

val Throwable.isPhotoEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.PHOTO_ENTITY_MISSING

val Throwable.isPhotoUnexpectedBehavior: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.PHOTO_UNEXPECTED_BEHAVIOR

val Throwable.isChatEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.CHAT_ENTITY_MISSING

val Throwable.isChatMessageEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.CHAT_MESSAGE_ENTITY_MISSING

val Throwable.isChatNoPermission: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.CHAT_NO_PERMISSION

val Throwable.isChatMultipleAttachmentsNotAllowed: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.CHAT_MULTIPLE_ATTACHMENTS_NOT_ALLOWED

val Throwable.isMultipleUsersChatsNotSupported: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.MULTIPLE_USERS_CHATS_NOT_SUPPORTED

val Throwable.isOrgCannotStartNewDialog: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORG_CANNOT_START_NEW_DIALOG

val Throwable.isRegistrationWrongConfirmCode: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.REGISTRATION_WRONG_CONFIRM_CODE

val Throwable.isRegistrationEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.REGISTRATION_ENTITY_MISSING

val Throwable.isRegistrationCodeAlreadyConfirmed: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.REGISTRATION_CODE_ALREADY_CONFIRMED

val Throwable.isRegistrationEntityExists: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.REGISTRATION_ENTITY_EXISTS

val Throwable.isRegistrationEntityNotFound: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.REGISTRATION_ENTITY_NOT_FOUND

val Throwable.isRegistrationUnexpectedBehavior: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.REGISTRATION_UNEXPECTED_BEHAVIOR

val Throwable.isRegistrationConfirmCodeNotElapsed: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.REGISTRATION_CONFIRM_CODE_NOT_ELAPSED

val Throwable.isRegistrationGoogleTokenExpired: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.REGISTRATION_GOOGLE_TOKEN_EXPIRED

val Throwable.isRegistrationVkAuthError: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.REGISTRATION_VK_AUTH_ERROR

val Throwable.isRegistrationUserEntityAlreadyExists: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.REGISTRATION_USER_ENTITY_ALREADY_EXISTS

val Throwable.isRegistrationConfirmCodeNotConfirmed: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.REGISTRATION_CONFIRM_CODE_NOT_CONFIRMED

val Throwable.isOrderNoResponsesError: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_NO_RESPONSES_ERROR

val Throwable.isOrderEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_ENTITY_MISSING

val Throwable.isOrderStatusConflict: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_STATUS_CONFLICT

val Throwable.isOrderCannotBeEdited: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_CANNOT_BE_EDITED

val Throwable.isOrderCannotBeFinished: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_CANNOT_BE_FINISHED

val Throwable.isOrderUserEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_USER_ENTITY_MISSING

val Throwable.isOrderEmployeeEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_EMPLOYEE_ENTITY_MISSING

val Throwable.isOrderFavorEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_FAVOR_ENTITY_MISSING

val Throwable.isOrderFavorSubcategoriesConflict: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_FAVOR_SUBCATEGORIES_CONFLICT

val Throwable.isOrderParticularEmployeeIdMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_PARTICULAR_EMPLOYEE_ID_MISSING

val Throwable.isOrderUncompletedCantBeUsedForPortfolio: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_UNCOMPLETED_CANT_BE_USED_FOR_PORTFOLIO

val Throwable.isOrderChangesCannotBeApplied: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_CHANGES_CANNOT_BE_APPLIED

val Throwable.isOrderPermissionDenied: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_PERMISSION_DENIED

val Throwable.isOrderChangesEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_CHANGES_ENTITY_MISSING

val Throwable.isOrderIsNotAVisit: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_IS_NOT_A_VISIT

val Throwable.isOrderNoActiveChanges: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORDER_NO_ACTIVE_CHANGES

val Throwable.isAddressEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ADDRESS_ENTITY_MISSING

val Throwable.isAddressCityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ADDRESS_CITY_MISSING

val Throwable.isAddressInvalidError: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ADDRESS_INVALID_ERROR

val Throwable.isFeedbackEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.FEEDBACK_ENTITY_MISSING

val Throwable.isFeedbackSortTypeMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.FEEDBACK_SORT_TYPE_MISSING

val Throwable.isFeedbackNoPermission: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.FEEDBACK_NO_PERMISSION

val Throwable.isFeedbackWearingEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.FEEDBACK_WEARING_ENTITY_MISSING

val Throwable.isFeedbackTooMuchPhotos: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.FEEDBACK_TOO_MUCH_PHOTOS

val Throwable.isEmployeeJobsEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.EMPLOYEE_JOBS_ENTITY_MISSING

val Throwable.isEmployeeNoPermission: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.EMPLOYEE_NO_PERMISSION

val Throwable.isEmployeeAlreadyExists: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.EMPLOYEE_ALREADY_EXISTS

val Throwable.isUserEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.USER_ENTITY_MISSING

val Throwable.isOrgEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.ORG_ENTITY_MISSING

val Throwable.isFavorEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.FAVOR_ENTITY_MISSING

val Throwable.isCalendarEntityMissing: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.CALENDAR_ENTITY_MISSING

val Throwable.isCalendarTooLongRange: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.CALENDAR_TOO_LONG_RANGE

val Throwable.isCalendarClosedAtDate: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.CALENDAR_CLOSED_AT_DATE

val Throwable.isCalendarNoPermission: Boolean
    get() = remoteError == ClientStatusCodes.RemoteError.CALENDAR_NO_PERMISSION
