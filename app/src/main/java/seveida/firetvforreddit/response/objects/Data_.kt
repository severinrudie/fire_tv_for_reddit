package seveida.firetvforreddit.response.objects

import com.squareup.moshi.Json

class Data_ {

    @Json(name = "approved_at_utc")
    var approvedAtUtc: Any
    @Json(name = "subreddit")
    var subreddit: String
    @Json(name = "selftext")
    var selftext: String
    @Json(name = "author_fullname")
    var authorFullname: String
    @Json(name = "saved")
    var saved: Boolean = false
    @Json(name = "mod_reason_title")
    var modReasonTitle: Any
    @Json(name = "gilded")
    var gilded: Int = 0
    @Json(name = "clicked")
    var clicked: Boolean = false
    @Json(name = "title")
    var title: String
    @Json(name = "link_flair_richtext")
    var linkFlairRichtext: List<Any>? = null
    @Json(name = "subreddit_name_prefixed")
    var subredditNamePrefixed: String
    @Json(name = "hidden")
    var hidden: Boolean = false
    @Json(name = "pwls")
    var pwls: Any
    @Json(name = "link_flair_css_class")
    var linkFlairCssClass: Any
    @Json(name = "downs")
    var downs: Int = 0
    @Json(name = "thumbnail_height")
    var thumbnailHeight: Int = 0
    @Json(name = "hide_score")
    var hideScore: Boolean = false
    @Json(name = "name")
    var name: String
    @Json(name = "quarantine")
    var quarantine: Boolean = false
    @Json(name = "link_flair_text_color")
    var linkFlairTextColor: String
    @Json(name = "author_flair_background_color")
    var authorFlairBackgroundColor: Any
    @Json(name = "subreddit_type")
    var subredditType: String
    @Json(name = "ups")
    var ups: Int = 0
    @Json(name = "domain")
    var domain: String
    @Json(name = "media_embed")
    var mediaEmbed: MediaEmbed
    @Json(name = "thumbnail_width")
    var thumbnailWidth: Int = 0
    @Json(name = "author_flair_template_id")
    var authorFlairTemplateId: Any
    @Json(name = "is_original_content")
    var isOriginalContent: Boolean = false
    @Json(name = "user_reports")
    var userReports: List<Any>? = null
    @Json(name = "secure_media")
    var secureMedia: Any
    @Json(name = "is_reddit_media_domain")
    var isRedditMediaDomain: Boolean = false
    @Json(name = "is_meta")
    var isMeta: Boolean = false
    @Json(name = "category")
    var category: Any
    @Json(name = "secure_media_embed")
    var secureMediaEmbed: SecureMediaEmbed
    @Json(name = "link_flair_text")
    var linkFlairText: Any
    @Json(name = "can_mod_post")
    var canModPost: Boolean = false
    @Json(name = "score")
    var score: Int = 0
    @Json(name = "approved_by")
    var approvedBy: Any
    @Json(name = "thumbnail")
    var thumbnail: String
    @Json(name = "edited")
    var edited: Boolean = false
    @Json(name = "author_flair_css_class")
    var authorFlairCssClass: Any
    @Json(name = "author_flair_richtext")
    var authorFlairRichtext: List<Any>? = null
    @Json(name = "gildings")
    var gildings: Gildings
    @Json(name = "post_hint")
    var postHint: String
    @Json(name = "content_categories")
    var contentCategories: Any
    @Json(name = "is_self")
    var isSelf: Boolean = false
    @Json(name = "mod_note")
    var modNote: Any
    @Json(name = "created")
    var created: Int = 0
    @Json(name = "link_flair_type")
    var linkFlairType: String
    @Json(name = "wls")
    var wls: Any
    @Json(name = "banned_by")
    var bannedBy: Any
    @Json(name = "author_flair_type")
    var authorFlairType: String
    @Json(name = "contest_mode")
    var contestMode: Boolean = false
    @Json(name = "selftext_html")
    var selftextHtml: Any
    @Json(name = "likes")
    var likes: Any
    @Json(name = "suggested_sort")
    var suggestedSort: Any
    @Json(name = "banned_at_utc")
    var bannedAtUtc: Any
    @Json(name = "view_count")
    var viewCount: Any
    @Json(name = "archived")
    var archived: Boolean = false
    @Json(name = "no_follow")
    var noFollow: Boolean = false
    @Json(name = "is_crosspostable")
    var isCrosspostable: Boolean = false
    @Json(name = "pinned")
    var pinned: Boolean = false
    @Json(name = "over_18")
    var over18: Boolean = false
    @Json(name = "preview")
    var preview: Preview
    @Json(name = "media_only")
    var mediaOnly: Boolean = false
    @Json(name = "link_flair_template_id")
    var linkFlairTemplateId: Any
    @Json(name = "can_gild")
    var canGild: Boolean = false
    @Json(name = "spoiler")
    var spoiler: Boolean = false
    @Json(name = "locked")
    var locked: Boolean = false
    @Json(name = "author_flair_text")
    var authorFlairText: Any
    @Json(name = "visited")
    var visited: Boolean = false
    @Json(name = "num_reports")
    var numReports: Any
    @Json(name = "distinguished")
    var distinguished: Any
    @Json(name = "subreddit_id")
    var subredditId: String
    @Json(name = "mod_reason_by")
    var modReasonBy: Any
    @Json(name = "removal_reason")
    var removalReason: Any
    @Json(name = "link_flair_background_color")
    var linkFlairBackgroundColor: String
    @Json(name = "id")
    var id: String
    @Json(name = "is_robot_indexable")
    var isRobotIndexable: Boolean = false
    @Json(name = "report_reasons")
    var reportReasons: Any
    @Json(name = "author")
    var author: String
    @Json(name = "num_crossposts")
    var numCrossposts: Int = 0
    @Json(name = "num_comments")
    var numComments: Int = 0
    @Json(name = "send_replies")
    var sendReplies: Boolean = false
    @Json(name = "whitelist_status")
    var whitelistStatus: Any
    @Json(name = "mod_reports")
    var modReports: List<Any>? = null
    @Json(name = "author_patreon_flair")
    var authorPatreonFlair: Boolean = false
    @Json(name = "author_flair_text_color")
    var authorFlairTextColor: Any
    @Json(name = "permalink")
    var permalink: String
    @Json(name = "parent_whitelist_status")
    var parentWhitelistStatus: Any
    @Json(name = "stickied")
    var stickied: Boolean = false
    @Json(name = "url")
    var url: String
    @Json(name = "subreddit_subscribers")
    var subredditSubscribers: Int = 0
    @Json(name = "created_utc")
    var createdUtc: Int = 0
    @Json(name = "media")
    var media: Any
    @Json(name = "is_video")
    var isVideo: Boolean = false

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param selftextHtml
     * @param pwls
     * @param mediaOnly
     * @param isOriginalContent
     * @param score
     * @param subredditId
     * @param authorFlairType
     * @param authorFlairCssClass
     * @param secureMediaEmbed
     * @param modReports
     * @param clicked
     * @param numComments
     * @param authorPatreonFlair
     * @param canGild
     * @param authorFullname
     * @param linkFlairText
     * @param created
     * @param linkFlairTemplateId
     * @param contestMode
     * @param over18
     * @param bannedBy
     * @param subredditSubscribers
     * @param modReasonTitle
     * @param postHint
     * @param createdUtc
     * @param stickied
     * @param linkFlairType
     * @param linkFlairCssClass
     * @param noFollow
     * @param saved
     * @param distinguished
     * @param mediaEmbed
     * @param linkFlairTextColor
     * @param url
     * @param authorFlairText
     * @param subreddit
     * @param reportReasons
     * @param authorFlairTemplateId
     * @param category
     * @param parentWhitelistStatus
     * @param thumbnail
     * @param permalink
     * @param preview
     * @param likes
     * @param gildings
     * @param isRedditMediaDomain
     * @param locked
     * @param thumbnailWidth
     * @param media
     * @param viewCount
     * @param whitelistStatus
     * @param quarantine
     * @param isCrosspostable
     * @param approvedBy
     * @param suggestedSort
     * @param isVideo
     * @param id
     * @param modReasonBy
     * @param visited
     * @param subredditNamePrefixed
     * @param userReports
     * @param author
     * @param approvedAtUtc
     * @param title
     * @param modNote
     * @param isRobotIndexable
     * @param archived
     * @param pinned
     * @param name
     * @param domain
     * @param sendReplies
     * @param isSelf
     * @param authorFlairBackgroundColor
     * @param numReports
     * @param secureMedia
     * @param edited
     * @param removalReason
     * @param linkFlairBackgroundColor
     * @param subredditType
     * @param spoiler
     * @param authorFlairRichtext
     * @param thumbnailHeight
     * @param authorFlairTextColor
     * @param contentCategories
     * @param gilded
     * @param linkFlairRichtext
     * @param hideScore
     * @param isMeta
     * @param hidden
     * @param numCrossposts
     * @param wls
     * @param canModPost
     * @param downs
     * @param ups
     * @param selftext
     * @param bannedAtUtc
     */
    constructor(approvedAtUtc: Any, subreddit: String, selftext: String, authorFullname: String, saved: Boolean, modReasonTitle: Any, gilded: Int, clicked: Boolean, title: String, linkFlairRichtext: List<Any>, subredditNamePrefixed: String, hidden: Boolean, pwls: Any, linkFlairCssClass: Any, downs: Int, thumbnailHeight: Int, hideScore: Boolean, name: String, quarantine: Boolean, linkFlairTextColor: String, authorFlairBackgroundColor: Any, subredditType: String, ups: Int, domain: String, mediaEmbed: MediaEmbed, thumbnailWidth: Int, authorFlairTemplateId: Any, isOriginalContent: Boolean, userReports: List<Any>, secureMedia: Any, isRedditMediaDomain: Boolean, isMeta: Boolean, category: Any, secureMediaEmbed: SecureMediaEmbed, linkFlairText: Any, canModPost: Boolean, score: Int, approvedBy: Any, thumbnail: String, edited: Boolean, authorFlairCssClass: Any, authorFlairRichtext: List<Any>, gildings: Gildings, postHint: String, contentCategories: Any, isSelf: Boolean, modNote: Any, created: Int, linkFlairType: String, wls: Any, bannedBy: Any, authorFlairType: String, contestMode: Boolean, selftextHtml: Any, likes: Any, suggestedSort: Any, bannedAtUtc: Any, viewCount: Any, archived: Boolean, noFollow: Boolean, isCrosspostable: Boolean, pinned: Boolean, over18: Boolean, preview: Preview, mediaOnly: Boolean, linkFlairTemplateId: Any, canGild: Boolean, spoiler: Boolean, locked: Boolean, authorFlairText: Any, visited: Boolean, numReports: Any, distinguished: Any, subredditId: String, modReasonBy: Any, removalReason: Any, linkFlairBackgroundColor: String, id: String, isRobotIndexable: Boolean, reportReasons: Any, author: String, numCrossposts: Int, numComments: Int, sendReplies: Boolean, whitelistStatus: Any, modReports: List<Any>, authorPatreonFlair: Boolean, authorFlairTextColor: Any, permalink: String, parentWhitelistStatus: Any, stickied: Boolean, url: String, subredditSubscribers: Int, createdUtc: Int, media: Any, isVideo: Boolean) : super() {
        this.approvedAtUtc = approvedAtUtc
        this.subreddit = subreddit
        this.selftext = selftext
        this.authorFullname = authorFullname
        this.saved = saved
        this.modReasonTitle = modReasonTitle
        this.gilded = gilded
        this.clicked = clicked
        this.title = title
        this.linkFlairRichtext = linkFlairRichtext
        this.subredditNamePrefixed = subredditNamePrefixed
        this.hidden = hidden
        this.pwls = pwls
        this.linkFlairCssClass = linkFlairCssClass
        this.downs = downs
        this.thumbnailHeight = thumbnailHeight
        this.hideScore = hideScore
        this.name = name
        this.quarantine = quarantine
        this.linkFlairTextColor = linkFlairTextColor
        this.authorFlairBackgroundColor = authorFlairBackgroundColor
        this.subredditType = subredditType
        this.ups = ups
        this.domain = domain
        this.mediaEmbed = mediaEmbed
        this.thumbnailWidth = thumbnailWidth
        this.authorFlairTemplateId = authorFlairTemplateId
        this.isOriginalContent = isOriginalContent
        this.userReports = userReports
        this.secureMedia = secureMedia
        this.isRedditMediaDomain = isRedditMediaDomain
        this.isMeta = isMeta
        this.category = category
        this.secureMediaEmbed = secureMediaEmbed
        this.linkFlairText = linkFlairText
        this.canModPost = canModPost
        this.score = score
        this.approvedBy = approvedBy
        this.thumbnail = thumbnail
        this.edited = edited
        this.authorFlairCssClass = authorFlairCssClass
        this.authorFlairRichtext = authorFlairRichtext
        this.gildings = gildings
        this.postHint = postHint
        this.contentCategories = contentCategories
        this.isSelf = isSelf
        this.modNote = modNote
        this.created = created
        this.linkFlairType = linkFlairType
        this.wls = wls
        this.bannedBy = bannedBy
        this.authorFlairType = authorFlairType
        this.contestMode = contestMode
        this.selftextHtml = selftextHtml
        this.likes = likes
        this.suggestedSort = suggestedSort
        this.bannedAtUtc = bannedAtUtc
        this.viewCount = viewCount
        this.archived = archived
        this.noFollow = noFollow
        this.isCrosspostable = isCrosspostable
        this.pinned = pinned
        this.over18 = over18
        this.preview = preview
        this.mediaOnly = mediaOnly
        this.linkFlairTemplateId = linkFlairTemplateId
        this.canGild = canGild
        this.spoiler = spoiler
        this.locked = locked
        this.authorFlairText = authorFlairText
        this.visited = visited
        this.numReports = numReports
        this.distinguished = distinguished
        this.subredditId = subredditId
        this.modReasonBy = modReasonBy
        this.removalReason = removalReason
        this.linkFlairBackgroundColor = linkFlairBackgroundColor
        this.id = id
        this.isRobotIndexable = isRobotIndexable
        this.reportReasons = reportReasons
        this.author = author
        this.numCrossposts = numCrossposts
        this.numComments = numComments
        this.sendReplies = sendReplies
        this.whitelistStatus = whitelistStatus
        this.modReports = modReports
        this.authorPatreonFlair = authorPatreonFlair
        this.authorFlairTextColor = authorFlairTextColor
        this.permalink = permalink
        this.parentWhitelistStatus = parentWhitelistStatus
        this.stickied = stickied
        this.url = url
        this.subredditSubscribers = subredditSubscribers
        this.createdUtc = createdUtc
        this.media = media
        this.isVideo = isVideo
    }

}
