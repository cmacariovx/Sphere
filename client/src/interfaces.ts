export interface UserAuth {
    accessToken: string | null;
    isLoggedIn: boolean;
    needsAuth: boolean;
    userData: basicUserData | null;
}

export interface accessToken {
    id: string;
    sub: string;
    exp: number;
    iat: number;
    token_type: 0 | 1;
}

export interface basicUserData {
    id: string;
    firstName: string;
    lastName: string;
    title: string;
    mainInterest: string;
    profilePictureUrl: string;
    bannerPictureUrl: string;
    validated: boolean;
    verified: boolean;
}

export interface loginPayload {
    accessToken: string;
    userData: basicUserData;
}

export interface authResponse {
    message: string;
    accessToken: string;
    userData: basicUserData;
}

export interface basicUserDataResponse {
    basicUserData: basicUserData;
    newAccessToken: string;
    newRefreshToken: null;
}

export interface MainUserData {
    id: string;
    firstName: string;
    lastName: string;
    title: string;
    about: string;
    assets: MainUserAssets;
    activity: MainUserActivity;
    verification: MainUserVerification;
    interests: MainUserInterests;
}

export interface MainUserAssets {
    profilePictureUrl: string;
    bannerPictureUrl: string;
}

export interface MainUserInterests {
    mainInterest: string;
    interestsCount: { [key: string]: number };
    topInterests: string[];
    circleIds: string[];
}

interface MainUserPosts {
    postIds: string[];
    totalUpvotes: number;
    totalDownvotes: number;
    totalComments: number;
    totalShares: number;
}

export interface MainUserActivity {
    posts: MainUserPosts;
    upvotedIds: string[];
    commentIds: string[];
}

export interface MainUserVerification {
    validated: boolean;
    verified: boolean;
}

export interface asyncFetchResponse {
    mainUserData: MainUserData | null;
    statusCode: number;
}

export interface errorInfo {
    message: string;
    statusCode: number;
    action: any;
}
