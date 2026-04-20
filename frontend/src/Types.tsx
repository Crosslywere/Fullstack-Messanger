export interface ErrorResponse {
  status: number;
  message: string;
}

export interface AuthRequest {
  username: string;
  password: string;
}

export interface AuthResponse {
  success: boolean;
  message: string;
}
