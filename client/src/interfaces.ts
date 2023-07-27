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
    id: string | null;
    firstName: string;
    lastName: string;
    about: string;
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
}
