export interface UserAuth {
    accessToken: string | null;
    isLoggedIn: boolean;
}

export interface access_token {
    id: string;
    sub: string;
    exp: number;
    iat: number;
    token_type: 0 | 1;
}
